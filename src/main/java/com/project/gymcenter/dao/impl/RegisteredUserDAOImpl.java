package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.RegisteredUserDAO;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            Date userDateBirth = rs.getDate(index++);
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
    public int add(RegisteredUser registeredUser) {
        return 0;
    }

    @Override
    public int update(RegisteredUser registeredUser) {
        return 0;
    }

    @Override
    public int delete(RegisteredUser registeredUser) {
        return 0;
    }
}
