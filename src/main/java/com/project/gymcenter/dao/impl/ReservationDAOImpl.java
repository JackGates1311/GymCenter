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

            Long periodId = rs.getLong(index++);
            Long userId = rs.getLong(index++);

            PeriodReserved periodReserved = new PeriodReserved(periodId, userId);

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

        String sqlQuery = "INSERT INTO periodreserved (periodId, userId) VALUES (?, ?);";

        jdbcTemplate.update(sqlQuery, shoppingCartItem.getPeriodId(), shoppingCartItem.getUserId());
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
}
