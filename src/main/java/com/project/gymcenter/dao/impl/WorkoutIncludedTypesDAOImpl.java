package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.WorkoutIncludedTypesDAO;
import com.project.gymcenter.model.WorkoutIncludedTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorkoutIncludedTypesDAOImpl implements WorkoutIncludedTypesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class WorkoutIncludedTypesRowMapper implements RowMapper<WorkoutIncludedTypes> {

        @Override
        public WorkoutIncludedTypes mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long workoutId = rs.getLong(index++);
            String workoutTypeName = rs.getString(index++);

            WorkoutIncludedTypes workoutIncludedTypes = new WorkoutIncludedTypes(workoutId, workoutTypeName);

            return workoutIncludedTypes;
        }
    }

    @Override
    public List<WorkoutIncludedTypes> findById(Long workoutId) {

        String sqlQuery = "SELECT * FROM workoutIncludedTypes WHERE workoutId = " + workoutId + ";";

        return jdbcTemplate.query(sqlQuery, new WorkoutIncludedTypesRowMapper());

    }

    @Override
    public void add(Long workoutId, String workoutTypeName) {

        String sqlQuery = "INSERT INTO workoutIncludedTypes (workoutId, workoutTypeName) VALUES (?, ?);";

        jdbcTemplate.update(sqlQuery,workoutId, workoutTypeName);

    }

    @Override
    public void remove(Long workoutId) {

        String sqlQuery = "DELETE FROM workoutIncludedTypes WHERE workoutId = ?";

        jdbcTemplate.update(sqlQuery, workoutId);

    }
}
