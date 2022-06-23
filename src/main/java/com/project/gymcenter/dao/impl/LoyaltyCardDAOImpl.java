package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.LoyaltyCardDAO;
import com.project.gymcenter.model.LoyaltyCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoyaltyCardDAOImpl implements LoyaltyCardDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private class LoyaltyCardRowMapper implements RowMapper<LoyaltyCard> {

        @Override
        public LoyaltyCard mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long loyaltyCardId = rs.getLong(index++);
            Long workoutId = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            Integer discount = rs.getInt(index++);
            Integer points = rs.getInt(index++);
            Boolean isApproved = rs.getBoolean(index++);

            return new LoyaltyCard(loyaltyCardId, workoutId, userId, discount, points, isApproved);
        }
    }

    @Override
    public void sendRequestForLoyaltyCard(Long userId) {

        int loyaltyCardId = generateLoyaltyCardId();

        String sqlQuery = "INSERT INTO loyaltycard(loyaltyCardId, workoutId, userId, discount, points, isApproved) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery,loyaltyCardId, 0, userId, 0, 0, null);
    }

    @Override
    public void approveLoyaltyCard(Long id) {

        String sqlQuery = "UPDATE loyaltycard SET isApproved = 1, points = 10 WHERE loyaltyCardId = ?;";

        System.out.println("Approved");

        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public void declineLoyaltyCard(Long id) {

        String sqlQuery = "UPDATE loyaltycard SET isApproved = 0 WHERE loyaltyCardId = ?;";

        System.out.println("Declined");

        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public List<LoyaltyCard> findLoyaltyCardRequests() {

        String sqlQuery = "SELECT * FROM loyaltycard WHERE isApproved IS NULL;";

        return jdbcTemplate.query(sqlQuery, new LoyaltyCardRowMapper());
    }

    @Override
    public boolean findIfUserIsAlreadyHaveLoyaltyCard(Long userId) {

        String sqlQuery = "SELECT * FROM loyaltycard WHERE userId = " + userId + " AND (isApproved = 1 " +
                "OR isApproved IS NULL)";

        List<LoyaltyCard> loyaltyCardFound = jdbcTemplate.query(sqlQuery, new LoyaltyCardRowMapper());

        if(loyaltyCardFound.isEmpty()) {

            return false;

        } else {

            return true;
        }

    }

    private int generateLoyaltyCardId() {

        String sqlQuery = "SELECT * FROM loyaltycard WHERE loyaltyCardId = " +
                "(SELECT MAX(loyaltycard.loyaltyCardId * 1) FROM loyaltyCard);";

        List<LoyaltyCard> loyaltyCard = jdbcTemplate.query(sqlQuery, new LoyaltyCardRowMapper());

        int generatedloyaltyCardId;

        try {

            generatedloyaltyCardId = Math.toIntExact(loyaltyCard.get(0).getLoyaltyCardId() + 1);

        } catch (Exception ignored) {

            generatedloyaltyCardId = 1;
        }

        return generatedloyaltyCardId;
    }
}
