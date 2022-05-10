package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.RegisteredUserDAO;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class RegisteredUserDAOImpl implements RegisteredUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class RegisteredUserRowMapper implements RowMapper<RegisteredUser> {

        @Override
        public RegisteredUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long userId = rs.getLong(index++);
            String userName = rs.getString(index++);
            String userPassword = rs.getString(index++);
            String userEmail = rs.getString(index++);
            String userFirstName = rs.getString(index++);
            String userLastName = rs.getString(index++);
            LocalDate userDateBirth = rs.getObject(index++, LocalDate.class);
            String userAddress = rs.getString(index++);
            String userPhoneNumber = rs.getString(index++);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime userDateTimeRegistration = LocalDateTime.parse(rs.getString(index++), formatter);

            UserRole userRole = UserRole.valueOf(rs.getString(index++));
            Boolean isRemoved = rs.getBoolean(index++);

            RegisteredUser registeredUser = new RegisteredUser(userId, userName, userPassword, userEmail,
                    userFirstName, userLastName, userDateBirth, userAddress, userPhoneNumber, userDateTimeRegistration,
                    userRole, isRemoved);

            return registeredUser;
        }
    }


    @Override
    public List<RegisteredUser> findAll() {

        String sqlQuery = "SELECT * FROM registeredUser";

        return jdbcTemplate.query(sqlQuery, new RegisteredUserRowMapper());
    }

    @Override
    public RegisteredUser findOne(String userName, String password) {

        try {

            String sqlQuery = "SELECT * FROM registeredUser WHERE userName = ? AND userPassword = ?";

            return jdbcTemplate.queryForObject(sqlQuery, new RegisteredUserRowMapper(), userName, password);

        } catch (EmptyResultDataAccessException ex) {

            return null;
        }

    }

    @Override
    public void add(RegisteredUser registeredUser) {

        String sqlQuery = "INSERT INTO registeredUser (userId, userName, userPassword, userEmail, userFirstName, " +
                "userLastName, userDateBirth, userAddress, userPhoneNumber, userDateTimeRegistration, userRole, " +
                "isDeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(sqlQuery,generateUserId(), registeredUser.getUserName(),
                registeredUser.getUserPassword(), registeredUser.getUserEmail(), registeredUser.getUserFirstName(),
                registeredUser.getUserLastName(), registeredUser.getUserDateBirth(), registeredUser.getUserAddress(),
                registeredUser.getUserPhoneNumber(), registeredUser.getUserDateTimeRegistration(), "Customer", 0);

    }

    @Override
    public void update(RegisteredUser registeredUser, int userId) {

        String sqlQuery = "UPDATE registeredUser SET userName = ?, userEmail = ?, " +
                "userFirstName = ?, userLastName = ?, userDateBirth = ?, userAddress = ?, userPhoneNumber = ? " +
                "WHERE userId = ?;";

        jdbcTemplate.update(sqlQuery, registeredUser.getUserName(), registeredUser.getUserEmail(),
                registeredUser.getUserFirstName(), registeredUser.getUserLastName(), registeredUser.getUserDateBirth(),
                registeredUser.getUserAddress(), registeredUser.getUserPhoneNumber(), userId);

    }

    @Override
    public int delete(RegisteredUser registeredUser) {
        return 0;
    }

    @Override
    public int generateUserId() {

        String sqlQuery = "SELECT * FROM registereduser WHERE userId = " +
                "(SELECT MAX(registereduser.userId * 1) FROM registeredUser)";

        List<RegisteredUser> registeredUser = jdbcTemplate.query(sqlQuery, new RegisteredUserRowMapper());

        int generatedUserId = Math.toIntExact(registeredUser.get(0).getUserId() + 1);

        return generatedUserId;
    }

    @Override
    public RegisteredUser findById(int userId) {

        String sqlQuery = "SELECT * FROM registeredUser WHERE userId = '" + userId + "';";

        List<RegisteredUser> registeredUser = jdbcTemplate.query(sqlQuery, new RegisteredUserRowMapper());

        return registeredUser.get(0);
    }

    @Override
    public void changePassword(String newPassword, int userId) {

        String sqlQuery = "UPDATE registeredUser SET userPassword = ? WHERE userId = ?";

        jdbcTemplate.update(sqlQuery, newPassword, userId);

    }

    @Override
    public List<RegisteredUser> find(String userName, String userRole, String userSortBy) {

        try {

            String sqlQuery = "SELECT * FROM registeredUser WHERE userName LIKE '%" + userName + "%' AND userRole " +
                    "LIKE '%" + userRole + "%' \n" + userSortBy;

            List<RegisteredUser> usersFound = jdbcTemplate.query(sqlQuery, new RegisteredUserDAOImpl.RegisteredUserRowMapper());

            return usersFound;

        } catch (EmptyResultDataAccessException ex) {

            return null;
        }

    }

}
