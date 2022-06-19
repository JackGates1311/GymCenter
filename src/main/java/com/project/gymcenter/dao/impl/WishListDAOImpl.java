package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.WishListDAO;
import com.project.gymcenter.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WishListDAOImpl implements WishListDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private class WishListRowMapper implements RowMapper<WishList> {

        @Override
        public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long wishListId = rs.getLong(index++);
            Long workoutId = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            String workoutName = rs.getString(index++);

            WishList wishList = new WishList(wishListId, workoutId, userId, workoutName);

            return wishList;
        }
    }

    @Override
    public List<WishList> getWishListByUserId(Long id) {

        String sqlQuery = "SELECT wishlist.wishListId, wishlist.workoutId, wishlist.userId, workout.workoutName FROM " +
                "wishlist INNER JOIN workout ON workout.workoutId = wishlist.workoutId WHERE wishlist.userId = " + id +
                ";";

        List<WishList> wishList = jdbcTemplate.query(sqlQuery, new WishListRowMapper());

        return wishList;
    }

    @Override
    public void deleteById(Long id) {

        String sqlQuery = "DELETE FROM wishlist WHERE wishListId = ?;";

        jdbcTemplate.update(sqlQuery, id);

    }

    @Override
    public boolean checkIfAlreadyExistsInWishList(Long id, Long userId) {


        try {

            String sqlQuery = "SELECT wishlist.wishListId, wishlist.workoutId, wishlist.userId, workout.workoutName " +
                    "FROM wishlist INNER JOIN workout ON workout.workoutId = wishlist.workoutId WHERE " +
                    "wishlist.workoutId = " + id + " AND wishlist.userId = " + userId + ";";

            List<WishList> wishListFound = jdbcTemplate.query(sqlQuery, new WishListRowMapper());

            if(wishListFound.isEmpty()) {

                return false;
            }

            return true;

        } catch (Exception e) {

            return false;
        }

    }

    @Override
    public void addWorkoutToWishList(Long id, Long userId) {

        int wishListId = generateWishListId();

        String sqlQuery = "INSERT INTO wishlist (wishListId, workoutId, userId) VALUES (?, ?, ?)";

        jdbcTemplate.update(sqlQuery, wishListId, id, userId);

    }


    //TODO remove multiple generators, you can create one instance of that function with two parameters
    private int generateWishListId() {

        String sqlQuery = "SELECT wishlist.wishListId, wishlist.workoutId, wishlist.userId, workout.workoutName FROM " +
                "wishlist INNER JOIN workout ON workout.workoutId = wishlist.workoutId WHERE wishListId = " +
                "(SELECT MAX(wishlist.wishListId * 1) FROM wishlist)";

        List<WishList> wishList = jdbcTemplate.query(sqlQuery, new WishListRowMapper());

        int generatedWishListId;

        try {

            generatedWishListId = Math.toIntExact(wishList.get(0).getWishListId() + 1);

        } catch (Exception e) {

            generatedWishListId = 1;
        }

        return generatedWishListId;
    }
}
