package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.WorkoutTypeDAO;
import com.project.gymcenter.model.WorkoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorkoutTypeDAOImpl implements WorkoutTypeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class WorkoutTypeRowMapper implements RowMapper<WorkoutType> {

        @Override
        public WorkoutType mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            String workoutTypeName = rs.getString(index++);
            String workoutTypeDetailedDescription = rs.getString(index++);
            String workoutImage = rs.getString(index++);

            WorkoutType workoutType = new WorkoutType(workoutTypeName, workoutTypeDetailedDescription, workoutImage);

            return workoutType;
        }
    }

    @Override
    public List<WorkoutType> findAll() {

        String sqlQuery = "SELECT * FROM workoutType";

        return jdbcTemplate.query(sqlQuery, new WorkoutTypeRowMapper());
    }
}
