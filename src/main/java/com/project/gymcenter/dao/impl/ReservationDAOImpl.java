package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.ReservationDAO;
import com.project.gymcenter.model.Auditorium;
import com.project.gymcenter.model.PeriodReserved;
import com.project.gymcenter.model.ShoppingCart;
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

        String sqlQuery = "SELECT periodreserved.periodId, periodreserved.userId FROM periodreserved INNER JOIN " +
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
