package com.project.gymcenter.dao.impl;

import com.mysql.cj.result.Row;
import com.project.gymcenter.dao.ShoppingCartDAO;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.model.WorkoutOrganizatonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class ShoppingCartRowMapper implements RowMapper<ShoppingCart> {

        @Override
        public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long shoppingCartId = rs.getLong(index++);
            Long periodId = rs.getLong(index++);
            Long userId = rs.getLong(index++);

            ShoppingCart shoppingCart = new ShoppingCart(shoppingCartId, periodId, userId);

            return shoppingCart;
        }
    }

    private class ShoppingCartFullRowMapper implements RowMapper<ShoppingCart> {

        @Override
        public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long shoppingCartId = rs.getLong(index++);
            Long periodId = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            Long auditoriumId = rs.getLong(index++);
            Long workoutId = rs.getLong(index++);
            LocalDateTime workoutDateTimeStart = rs.getObject(index++, LocalDateTime.class);
            LocalDateTime workoutDateTimeEnd = rs.getObject(index++, LocalDateTime.class);
            String workoutName = rs.getString(index++);
            String workoutCoaches = rs.getString(index++);
            Double workoutPrice = rs.getDouble(index++);
            WorkoutOrganizatonType workoutOrganizatonType = WorkoutOrganizatonType.valueOf(rs.getString(index++));

            ShoppingCart shoppingCart = new ShoppingCart(shoppingCartId, periodId, userId, auditoriumId, workoutId,
                    workoutDateTimeStart, workoutDateTimeEnd, workoutName, workoutCoaches, workoutPrice,
                    workoutOrganizatonType);

            return shoppingCart;
        }
    }

    @Override
    public void addWorkoutToShoppingCart(ShoppingCart shoppingCart) {

        int shoppingCartId = generateShoppingCartId();

        String sqlQuery = "INSERT INTO shoppingCart (shoppingCartId, periodId, userId) VALUES (?, ?, ?);";

        jdbcTemplate.update(sqlQuery, shoppingCartId, shoppingCart.getPeriodId(), shoppingCart.getUserId());

    }

    @Override
    public boolean checkIfAlreadyAddedToShoppingCart(ShoppingCart shoppingCart) {

        try {

            String sqlQuery = "SELECT * FROM shoppingCart WHERE userId = " + shoppingCart.getUserId() +
                    " AND periodId = " + shoppingCart.getPeriodId() + ";";

            List<ShoppingCart> shoppingCartList = jdbcTemplate.query(sqlQuery, new ShoppingCartRowMapper());

            if(shoppingCartList.isEmpty()) {

                return false;
            }

            return true;

        } catch (EmptyResultDataAccessException ex) {

            return false;
        }

    }

    @Override
    public List<ShoppingCart> findAllByUserId(Long id) {

        String sqlQuery = "SELECT shoppingCart.shoppingCartId, shoppingCart.periodId, shoppingCart.userId, \n" +
                "period.auditoriumId, period.workoutId, period.workoutDateTimeStart, period.workoutDateTimeEnd,\n" +
                "workout.workoutName, workout.workoutCoaches, workout.workoutPrice, workout.workoutOrganizationType " +
                "FROM shoppingCart \n" + "LEFT OUTER JOIN period ON shoppingCart.periodId = period.periodId \n" +
                "LEFT OUTER JOIN workout ON workout.workoutId = period.workoutId WHERE shoppingCart.userId = " + id +
                " ORDER BY period.workoutDateTimeStart ASC;";

        List<ShoppingCart> shoppingCartItemsFound = jdbcTemplate.query(sqlQuery, new ShoppingCartFullRowMapper());

        return shoppingCartItemsFound;
    }

    @Override
    public void deleteById(Long id) {

        String sqlQuery = "DELETE FROM shoppingCart WHERE shoppingCartId = ?;";

        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public int generateShoppingCartId() {

        String sqlQuery = "SELECT * FROM shoppingCart WHERE shoppingCartId = " +
                "(SELECT MAX(shoppingCart.shoppingCartId * 1) FROM shoppingCart)";

        List<ShoppingCart> shoppingCartList = jdbcTemplate.query(sqlQuery, new ShoppingCartRowMapper());

        int generatedShoppingCartId = Math.toIntExact(shoppingCartList.get(0).getShoppingCartId() + 1);

        return generatedShoppingCartId;
    }
}
