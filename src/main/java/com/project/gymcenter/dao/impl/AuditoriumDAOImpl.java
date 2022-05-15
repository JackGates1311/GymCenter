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

        String sqlQuery = "SELECT * FROM auditorium WHERE isDeleted = 0;";

        return jdbcTemplate.query(sqlQuery, new AuditoriumRowMapper());
    }

    @Override
    public List<Auditorium> find(String auditoriumId, String auditoriumSortBy) {

        try {

            String sqlQuery = "SELECT * FROM auditorium WHERE auditoriumId LIKE '%" + auditoriumId + "%' \n" +
                    auditoriumSortBy;

            List<Auditorium> auditoriumsFound = jdbcTemplate.query(sqlQuery, new AuditoriumDAOImpl.AuditoriumRowMapper());

            return auditoriumsFound;

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public void update(Auditorium auditorium) {

        String sqlQuery = "UPDATE auditorium SET capacity = ? WHERE auditoriumId = ?" + ";";

        jdbcTemplate.update(sqlQuery, auditorium.getCapacity(), auditorium.getAuditoriumId());

    }

    @Override
    public int remove(String id) {

        String sqlQuery= "UPDATE auditorium LEFT OUTER JOIN period ON auditorium.auditoriumId = period.auditoriumId " +
                "SET auditorium.isDeleted = 1 WHERE auditorium.auditoriumId = ? " +
                "AND (SELECT workoutDateTimeEnd FROM period WHERE auditoriumId = ? AND workoutDateTimeEnd > NOW()) " +
                "IS NULL;";

        return jdbcTemplate.update(sqlQuery, id, id);

    }

    @Override
    public void add(Auditorium auditorium) {

        String sqlQuery = "INSERT INTO auditorium (auditoriumId, capacity, isDeleted) VALUES (?, ?, ?);";

        jdbcTemplate.update(sqlQuery, auditorium.getAuditoriumId(), auditorium.getCapacity(), 0);

    }
}
