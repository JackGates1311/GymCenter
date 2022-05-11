package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.AuditoriumDAO;
import com.project.gymcenter.model.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuditoriumDAOImpl implements AuditoriumDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class AuditoriumRowMapper implements RowMapper<Auditorium> {


        @Override
        public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            String auditoriumId = rs.getString(index++);
            Long capacity = rs.getLong(index++);
            Boolean isDeleted = rs.getBoolean(index++);

            Auditorium auditorium = new Auditorium(auditoriumId, capacity, isDeleted);

            return auditorium;

        }
    }

    @Override
    public List<Auditorium> findAll() {

        String sqlQuery = "SELECT * FROM auditorium;";

        return jdbcTemplate.query(sqlQuery, new AuditoriumRowMapper());
    }
}
