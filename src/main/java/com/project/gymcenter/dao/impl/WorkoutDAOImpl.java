package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.WorkoutDAO;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutLevel;
import com.project.gymcenter.model.WorkoutOrganizatonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            String workoutTypeName = rs.getString(index++);
            String workoutCoaches = rs.getString(index++);
            String workoutDescription = rs.getString(index++);
            Double workoutPrice = rs.getDouble(index++);
            WorkoutOrganizatonType workoutOrganizatonType = WorkoutOrganizatonType.valueOf(rs.getString(index++));
            WorkoutLevel workoutLevel = WorkoutLevel.valueOf(rs.getString(index++));
            int workoutLength = rs.getInt(index++);
            Double workoutAverageRate = rs.getDouble(index++);
            Boolean isDeleted = rs.getBoolean(index++);

            Workout workout = new Workout (workoutId, workoutTypeName, workoutCoaches, workoutDescription, workoutPrice,
                    workoutOrganizatonType, workoutLevel, workoutLength, workoutAverageRate, isDeleted);

            return workout;
        }
    }

    @Override
    public Workout findOne(Long workoutId) {
        return null;
    }

    @Override
    public List<Workout> findAll() {

        String sqlQuery = "SELECT * FROM workout";

        return jdbcTemplate.query(sqlQuery, new WorkoutRowMapper());
    }
}
