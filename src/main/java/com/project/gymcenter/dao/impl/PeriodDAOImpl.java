package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.PeriodDAO;
import com.project.gymcenter.model.Auditorium;
import com.project.gymcenter.model.Period;
import com.project.gymcenter.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PeriodDAOImpl implements PeriodDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class PeriodRowMapper implements RowMapper<Period> {

        @Override
        public Period mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long periodId = rs.getLong(index++);
            String auditoriumId = rs.getString(index++);
            Long workoutId = rs.getLong(index++);
            LocalDateTime workoutDateTimeStart = rs.getObject(index++, LocalDateTime.class);
            LocalDateTime workoutDateTimeEnd = rs.getObject(index++, LocalDateTime.class);

            Period period = new Period(periodId, auditoriumId, workoutId, workoutDateTimeStart, workoutDateTimeEnd);

            return period;
        }
    }


    @Override
    public void add(Period period) {

        String sqlQuery = "INSERT INTO period (periodId, auditoriumId, workoutId, workoutDateTimeStart, workoutDateTimeEnd) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, generatePeriodId(), period.getAuditoriumId(), period.getWorkoutId(), period.getWorkoutDateTimeStart(),
                period.getWorkoutDateTimeEnd());

    }

    @Override
    public Boolean checkForPeriodAvailability(Period period) {

        String sqlQuery = "SELECT * FROM period WHERE auditoriumId = '" + period.getAuditoriumId() +
                "' AND (workoutDateTimeStart BETWEEN '" + period.getWorkoutDateTimeStart() + "' AND '" +
                period.getWorkoutDateTimeEnd() + "' OR " + "workoutDateTimeEnd BETWEEN '" +
                period.getWorkoutDateTimeStart() + "' AND '" + period.getWorkoutDateTimeEnd() + "' OR " +
                "(workoutDateTimeStart <= '" + period.getWorkoutDateTimeStart() +
                "' AND workoutDateTimeEnd >= '" + period.getWorkoutDateTimeEnd() + "'));";

        List<Period> periodsFound = jdbcTemplate.query(sqlQuery, new PeriodDAOImpl.PeriodRowMapper());

        if(periodsFound.isEmpty()) {

            return true;

        } else {

            return false;
        }

    }

    @Override
    public List<Period> findAvailablePeriodsByWorkoutId(Long id, Long userId) {

        String sqlQuery = "SELECT DISTINCT period.periodId, period.auditoriumId, period.workoutId," +
                "period.workoutDateTimeStart, period.workoutDateTimeEnd, periodreserved.periodId " +
                "FROM period LEFT OUTER JOIN periodreserved ON " +
                "period.periodId = periodreserved.periodId WHERE workoutId = " + id + " AND " +
                "workoutDateTimeStart > NOW()" + " AND (periodreserved.userId != " + userId + " OR " +
                "periodreserved.userId IS NULL)";

        List<Period> availablePeriods = jdbcTemplate.query(sqlQuery, new PeriodDAOImpl.PeriodRowMapper());

        return availablePeriods;

    }

    @Override
    public List<Period> findAllAvailablePeriods(Long userId) {

        /*String sqlQuery = "SELECT * FROM period WHERE workoutDateTimeStart > NOW()" +
                "ORDER BY workoutDateTimeStart;";*/

        String sqlQuery = "SELECT DISTINCT period.periodId, period.auditoriumId, period.workoutId, " +
                "period.workoutDateTimeStart, period.workoutDateTimeEnd, periodreserved.periodId FROM period " +
                "LEFT OUTER JOIN periodreserved ON " + "period.periodId = periodreserved.periodId WHERE " +
                "workoutDateTimeStart > NOW()" + " AND (periodreserved.userId != " + userId + " OR " +
                "periodreserved.userId IS NULL)";

        List<Period> availablePeriods = jdbcTemplate.query(sqlQuery, new PeriodDAOImpl.PeriodRowMapper());

        return availablePeriods;
    }

    @Override
    public Period findByPeriodId(Long periodId) {

        String sqlQuery = "SELECT * FROM period WHERE periodId = " + periodId + ";";

        List<Period> periodFound = jdbcTemplate.query(sqlQuery, new PeriodRowMapper());

        return periodFound.get(0);
    }

    @Override
    public boolean checkForPeriodAvailability(Long periodId) {

        String sqlQuery = "SELECT * FROM period WHERE periodId = " + periodId + " AND workoutDateTimeStart >= NOW();";

        List<Period> periodFound = jdbcTemplate.query(sqlQuery, new PeriodRowMapper());

        if(periodFound.isEmpty()) {

            return false;

        } else {

            return true;
        }

    }

    @Override
    public int generatePeriodId() {

        String sqlQuery = "SELECT * FROM period WHERE periodId = " +
                "(SELECT MAX(period.periodId * 1) FROM period)";

        List<Period> period = jdbcTemplate.query(sqlQuery, new PeriodDAOImpl.PeriodRowMapper());

        int generatedPeriodId = Math.toIntExact(period.get(0).getPeriodId() + 1);

        return generatedPeriodId;
    }


}
