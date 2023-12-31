package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.WorkoutDAO;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutLevel;
import com.project.gymcenter.model.WorkoutOrganizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkoutDAOImpl implements WorkoutDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class WorkoutRowMapper implements RowMapper<Workout> {


        @Override
        public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long workoutId = rs.getLong(index++);
            Long reservationCount = rs.getLong(index++);

            Workout workout = new Workout(workoutId, reservationCount);

            return workout;
        }
    }

    private class WorkoutFullRowMapper implements RowMapper<Workout> {

        @Override
        public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long workoutId = rs.getLong(index++);
            String workoutTypeName = rs.getString(index++);
            String workoutCoaches = rs.getString(index++);
            String workoutDescription = rs.getString(index++);
            Double workoutPrice = rs.getDouble(index++);
            WorkoutOrganizationType workoutOrganizationType = WorkoutOrganizationType.valueOf(rs.getString(index++));
            WorkoutLevel workoutLevel = WorkoutLevel.valueOf(rs.getString(index++));
            int workoutLength = rs.getInt(index++);
            Double workoutAverageRate = rs.getDouble(index++);
            Boolean isDeleted = rs.getBoolean(index++);
            String workoutName = rs.getString(index++);
            String workoutImage = rs.getString(index++);

            Workout workout = new Workout (workoutId, workoutTypeName, workoutCoaches, workoutDescription, workoutPrice,
                    workoutOrganizationType, workoutLevel, workoutLength, workoutAverageRate, isDeleted, workoutName,
                    workoutImage);

            return workout;
        }

    }

    @Override
    public Workout findById(Long workoutId) {

        String sqlQuery = "SELECT * FROM workout WHERE workoutId = " + workoutId + ";";

        WorkoutFullRowMapper WorkoutFullRowMapper = new WorkoutFullRowMapper();

        List<Workout> workout = jdbcTemplate.query(sqlQuery, new WorkoutFullRowMapper());

        return workout.get(0);
    }

    @Override
    public List<Workout> findAll() {

        String sqlQuery = "SELECT * FROM workout";

        return jdbcTemplate.query(sqlQuery, new WorkoutFullRowMapper());
    }

    @Override
    public Long add(Workout workout) {

        int workoutId = generateWorkoutId();

        String sqlQuery = "INSERT INTO workout (workoutId, workoutTypeName, workoutCoaches, workoutDescription, " +
                "workoutPrice, workoutOrganizationType, workoutLevel, workoutLength, workoutAverageRate, " +
                "isDeleted, workoutName, workoutImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(sqlQuery,workoutId, workout.getWorkoutTypeName(), workout.getWorkoutCoaches(),
                workout.getWorkoutDescription(), workout.getWorkoutPrice(),
                workout.getWorkoutOrganizationType().toString(), workout.getWorkoutLevel().toString(),
                workout.getWorkoutLength(), 0, 0, workout.getWorkoutName(), workout.getWorkoutImage());

        return Long.valueOf(workoutId);
    }

    @Override
    public void update(Workout workout, Long workoutId) {

        String sqlQuery = "UPDATE workout SET workoutTypeName = ?, workoutCoaches = ?, workoutDescription = ?, " +
                "workoutPrice = ?, workoutOrganizationType = ?, workoutLevel = ?, workoutLength = ?, workoutName = ?," +
                "workoutImage = ? WHERE workoutId = ?";

        jdbcTemplate.update(sqlQuery, workout.getWorkoutTypeName(), workout.getWorkoutCoaches(),
                workout.getWorkoutDescription(), workout.getWorkoutPrice(),
                workout.getWorkoutOrganizationType().toString(), workout.getWorkoutLevel().toString(),
                workout.getWorkoutLength(), workout.getWorkoutName(), workout.getWorkoutImage(), workoutId);

    }

    @Override
    public int delete(Workout workout) {
        return 0;
    }

    @Override
    public List<Workout> find(String workoutName, String workoutCoaches, Double workoutPriceMin,
                              Double workoutPriceMax, String workoutTypeName, String workoutOrganizationType,
                              String workoutLevel, String workoutSortBy) {

        ArrayList<Object> args = new ArrayList<Object>();

        try {

            String sqlQuery = "SELECT * FROM workout\n" +
                    "WHERE workoutName LIKE '%" + workoutName + "%' AND workoutCoaches LIKE '%"
                    + workoutCoaches + "%' AND " + "(workoutPrice BETWEEN " + workoutPriceMin + " AND " +
                    workoutPriceMax + ") AND workoutTypeName LIKE '%" + workoutTypeName + "%' AND\n" +
                    "workoutOrganizationType LIKE '%" + workoutOrganizationType + "%' AND " +
                    "workoutLevel LIKE '%" + workoutLevel + "%'\n " + workoutSortBy + ";";


            List<Workout> workoutsFound = jdbcTemplate.query(sqlQuery, new WorkoutFullRowMapper());

            return workoutsFound;

        } catch (EmptyResultDataAccessException ex) {

            return null;
        }

    }

    @Override
    public List<Workout> count() {

        String sqlQuery = "SELECT workout.workoutId, COUNT(workout.workoutId) AS reservationCount FROM workout\n" +
                "INNER JOIN period ON workout.workoutId = period.workoutId\n" +
                "INNER JOIN periodreserved ON period.periodId = periodreserved.periodId\n" +
                "GROUP BY workout.workoutId;";

        List<Workout> workoutsFound = jdbcTemplate.query(sqlQuery, new WorkoutRowMapper());

        return workoutsFound;
    }

    @Override
    public List<Workout> countBetweenDates(String summaryDateStart, String summaryDateEnd) {

        String sqlQuery = "SELECT workout.workoutId, COUNT(workout.workoutId) AS reservationCount FROM workout\n" +
                "INNER JOIN period ON workout.workoutId = period.workoutId\n" +
                "INNER JOIN periodreserved ON period.periodId = periodreserved.periodId\n" +
                "WHERE periodreserved.periodDateTimeReservation BETWEEN '" + summaryDateStart + "' AND '" +
                 summaryDateEnd + "'" + "GROUP BY workout.workoutId;";

        List<Workout> workoutsFound = jdbcTemplate.query(sqlQuery, new WorkoutRowMapper());

        return workoutsFound;
    }

    @Override
    public void updateAverageRate(Workout workout) {

        String sqlQuery = "UPDATE workout SET workoutAverageRate = ? WHERE workoutId = ?";

        jdbcTemplate.update(sqlQuery, workout.getWorkoutAverageRate(), workout.getWorkoutId());
    }

    @Override
    public int generateWorkoutId() {

        String sqlQuery = "SELECT * FROM workout WHERE workoutId = " +
                "(SELECT MAX(workout.workoutId * 1) FROM workout)";

        List<Workout> workouts = jdbcTemplate.query(sqlQuery, new WorkoutFullRowMapper());

        int generatedWorkoutId = Math.toIntExact(workouts.get(0).getWorkoutId() + 1);

        return generatedWorkoutId;
    }

}
