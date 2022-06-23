package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.ReservationDAO;
import com.project.gymcenter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private class ReservationCapacityRowMapper implements RowMapper<Auditorium> {

        @Override
        public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long capacity = rs.getLong(index++);

            Auditorium auditorium = new Auditorium(capacity);

            return auditorium;
        }
    }

    private class ReservationRowMapper implements RowMapper<PeriodReserved> {

        @Override
        public PeriodReserved mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long periodReservationId = rs.getLong(index++);
            Long periodId = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            LocalDateTime periodDateTimeReservation = rs.getObject(index++, LocalDateTime.class);

            PeriodReserved periodReserved = new PeriodReserved(periodReservationId, periodId, userId,
                    periodDateTimeReservation);

            return periodReserved;
        }
    }

    private class ReservationRowFullMapper implements RowMapper<PeriodReserved> {

        @Override
        public PeriodReserved mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long periodReservationId = rs.getLong(index++);
            Long periodId = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            LocalDateTime periodDateTimeReservation = rs.getObject(index++, LocalDateTime.class);

            //TODO refactor code here

            Long auditoriumId = rs.getLong(index++);

            Long workoutId = rs.getLong(index++);
            LocalDateTime workoutDateTimeStart = rs.getObject(index++, LocalDateTime.class);
            LocalDateTime workoutDateTimeEnd = rs.getObject(index++, LocalDateTime.class);
            String workoutName = rs.getString(index++);
            String workoutCoaches = rs.getString(index++);
            Double workoutPrice = rs.getDouble(index++);
            WorkoutOrganizationType workoutOrganizationType = WorkoutOrganizationType.valueOf(rs.getString(index++));

            String userName;
            UserRole userRole;

            try {

                userName = rs.getString(index++);
                userRole = UserRole.valueOf(rs.getString(index++));

            } catch (Exception e) {

                userName = null;
                userRole = null;
            }

            return new PeriodReserved(periodReservationId, periodId, userId,
                    periodDateTimeReservation, auditoriumId, workoutId, workoutDateTimeStart, workoutDateTimeEnd,
                    workoutName, workoutCoaches, workoutPrice, workoutOrganizationType, userName, userRole);
        }
    }

    @Override
    public boolean checkReservationAvailability(Long shoppingCartId) {

        String sqlQuery = "SELECT capacity FROM auditorium WHERE auditoriumId = (SELECT period.auditoriumId FROM shoppingcart\n" +
                " INNER JOIN period ON shoppingcart.periodId = period.periodId WHERE shoppingcart.shoppingCartId = " +
                shoppingCartId + ") AND\n" + " (SELECT COUNT(periodId) FROM periodreserved WHERE periodId = " +
                "(SELECT period.periodId FROM shoppingcart INNER JOIN period ON shoppingcart.periodId = period.periodId " +
                "WHERE shoppingcart.shoppingCartId = " + shoppingCartId + ")) " +
                "< capacity;";

        List<Auditorium> auditoriumCapacity = jdbcTemplate.query(sqlQuery, new ReservationCapacityRowMapper());

        if(auditoriumCapacity.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public void reserveWorkout(ShoppingCart shoppingCartItem) {

        String sqlQuery = "INSERT INTO periodreserved (periodReservationId, periodId, userId, " +
                "periodDateTimeReservation) VALUES (?, ?, ?, ?);";

        jdbcTemplate.update(sqlQuery, generatePeriodReservationId(), shoppingCartItem.getPeriodId(),
                shoppingCartItem.getUserId(), LocalDateTime.now());
    }

    @Override
    public boolean checkReservationTimeOverlapping(ShoppingCart shoppingCart) {

        String sqlQuery = "SELECT periodreserved.periodReservationId, periodreserved.periodId, periodreserved.userId, " +
                "periodreserved.periodDateTimeReservation FROM periodreserved INNER JOIN " +
                "period ON period.periodId = periodreserved.periodId WHERE periodreserved.userId = " +
                shoppingCart.getUserId() +
                " AND (period.workoutDateTimeStart BETWEEN '" + shoppingCart.getWorkoutDateTimeStart() + "' AND '" +
                shoppingCart.getWorkoutDateTimeEnd() + "' OR " + "period.workoutDateTimeEnd BETWEEN '" +
                shoppingCart.getWorkoutDateTimeStart() + "' AND '" + shoppingCart.getWorkoutDateTimeEnd() + "' OR " +
                "(period.workoutDateTimeStart <= '" + shoppingCart.getWorkoutDateTimeStart() +
                "' AND period.workoutDateTimeEnd >= '" + shoppingCart.getWorkoutDateTimeEnd() + "'));";

        List<PeriodReserved> periodsFound = jdbcTemplate.query(sqlQuery, new ReservationRowMapper());

        if(periodsFound.isEmpty()) {

            return true;

        } else {

            return false;
        }
    }

    @Override
    public List<PeriodReserved> findAllByUserId(Long id) {

        String sqlQuery = "SELECT periodreserved.periodReservationId, periodreserved.periodId, periodreserved.userId, " +
                "periodreserved.periodDateTimeReservation, \n" + "period.auditoriumId, period.workoutId, " +
                "period.workoutDateTimeStart, period.WorkoutDateTimeEnd, \n" + "workout.workoutName, " +
                "workout.workoutCoaches, workout.workoutPrice, workout.workoutOrganizationType\n" +
                "FROM periodreserved INNER JOIN period ON periodreserved.periodid = period.periodId " +
                "INNER JOIN workout ON workout.workoutId = period.workoutId\n" +
                "WHERE periodreserved.userId = " + id + " ORDER BY periodreserved.periodDateTimeReservation DESC;";

        return jdbcTemplate.query(sqlQuery, new ReservationRowFullMapper());
    }

    @Override
    public void deleteById(Long id) {

        String sqlQuery = "DELETE FROM periodreserved WHERE periodReservationId = ?;";

        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public List<PeriodReserved> findAll() {

        String sqlQuery = "SELECT periodreserved.periodReservationId, periodreserved.periodId, periodreserved.userId, " +
                "periodreserved.periodDateTimeReservation, \n" + "period.auditoriumId, period.workoutId, " +
                "period.workoutDateTimeStart, period.WorkoutDateTimeEnd, \n" + "workout.workoutName, " +
                "workout.workoutCoaches, workout.workoutPrice, workout.workoutOrganizationType, " +
                "registereduser.userName, registereduser.userRole\n" + "FROM periodreserved " +
                "INNER JOIN period ON periodreserved.periodid = period.periodId " +
                "INNER JOIN workout ON workout.workoutId = period.workoutId\n " +
                "INNER JOIN registereduser ON periodreserved.userId = registereduser.userId" +
                " ORDER BY periodreserved.periodDateTimeReservation DESC;";

        return jdbcTemplate.query(sqlQuery, new ReservationRowFullMapper());
    }

    @Override
    public List<PeriodReserved> find(String customerFilter, String reservationSortBy) {

        String sqlQuery = "SELECT periodreserved.periodReservationId, periodreserved.periodId, periodreserved.userId, " +
                "periodreserved.periodDateTimeReservation, \n" + "period.auditoriumId, period.workoutId, " +
                "period.workoutDateTimeStart, period.WorkoutDateTimeEnd, \n" + "workout.workoutName, " +
                "workout.workoutCoaches, workout.workoutPrice, workout.workoutOrganizationType, registereduser.userName, " +
                "registereduser.userRole\n" + "FROM periodreserved " +
                "INNER JOIN period ON periodreserved.periodid = period.periodId " +
                "INNER JOIN workout ON workout.workoutId = period.workoutId\n " +
                "INNER JOIN registereduser ON periodreserved.userId = registereduser.userId " +
                "WHERE registereduser.userId LIKE '%" + customerFilter + "%' " + reservationSortBy;

        return jdbcTemplate.query(sqlQuery, new ReservationRowFullMapper());
    }

    @Override
    public boolean checkUserAbilityToCommentThisWorkout(Long userId, Long workoutId) {

        String sqlQuery = "SELECT periodreserved.periodReservationId, periodreserved.periodId, periodreserved.userId, " +
                "periodReserved.periodDateTimeReservation FROM periodreserved " +
                "INNER JOIN period ON periodreserved.periodId = period.periodId WHERE userId = " + userId + "\n" +
                "AND period.workoutId = " + workoutId + " AND period.workoutDateTimeEnd < NOW();";

        List<PeriodReserved> periodReservedListFound = jdbcTemplate.query(sqlQuery, new ReservationRowMapper());

        if(periodReservedListFound.isEmpty()) {

            return false;

        } else {

            return true;
        }

    }

    private int generatePeriodReservationId() {

        String sqlQuery = "SELECT * FROM periodreserved WHERE periodReservationId = " +
                "(SELECT MAX(periodreserved.periodReservationId * 1) FROM periodreserved)";

        List<PeriodReserved> periodReservedList = jdbcTemplate.query(sqlQuery, new ReservationRowMapper());

        int generatedPeriodReservationId;

        try {

            generatedPeriodReservationId = Math.toIntExact(periodReservedList.get(0).getPeriodReservationId() + 1);

        } catch (Exception e) {

            generatedPeriodReservationId = 1;
        }

        return generatedPeriodReservationId;
    }

}
