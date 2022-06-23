package com.project.gymcenter.dao.impl;

import com.project.gymcenter.dao.WorkoutCommentDAO;
import com.project.gymcenter.model.CommentStatus;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WorkoutCommentDAOImpl implements WorkoutCommentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private class WorkoutCommentRowMapper implements RowMapper<WorkoutComment> {

        @Override
        public WorkoutComment mapRow(ResultSet rs, int rowNum) throws SQLException {

            int index = 1;

            Long commentId = rs.getLong(index++);
            Long userId = rs.getLong(index++);
            Long workoutId = rs.getLong(index++);
            Double workoutRate = rs.getDouble(index++);
            LocalDateTime commentDateTimePosted = rs.getObject(index++, LocalDateTime.class);
            String commentContent = rs.getString(index++);
            CommentStatus commentStatus = CommentStatus.valueOf(rs.getString(index++));
            Boolean postCommentAsGuest = rs.getBoolean(index++);

            String userName;

            try{

                userName = rs.getString(index++);

            }catch(Exception ignored) {

                userName = null;
            }

            WorkoutComment workoutComment = new WorkoutComment(commentId, userId, workoutId, workoutRate,
                    commentDateTimePosted, commentContent, commentStatus, postCommentAsGuest, userName);

            return workoutComment;
        }
    }

    @Override
    public List<WorkoutComment> findAllByByWorkoutId(Long id) {

        String sqlQuery = "SELECT workoutComment.commentId, workoutComment.userId, workoutComment.workoutId, " +
                "workoutComment.workoutRate, " + "workoutComment.commentDateTimePosted, " +
                "workoutComment.commentContent, workoutComment.commentStatus, workoutComment.postCommentAsGuest, " +
                "registereduser.userName FROM workoutComment " +
                "INNER JOIN registereduser ON registereduser.userId = workoutComment.userId " +
                "WHERE workoutId = " + id + " AND commentStatus = 'Approved';";

        return jdbcTemplate.query(sqlQuery, new WorkoutCommentRowMapper());
    }

    @Override
    public boolean checkIfUserIsAlreadyCommentedOnWorkout(Long userId, Long workoutId) {

        String sqlQuery = "SELECT * FROM workoutcomment where userId = " + userId + " AND workoutId = " +
                workoutId + ";";

        List<WorkoutComment> commentsFound = jdbcTemplate.query(sqlQuery, new WorkoutCommentRowMapper());

        if(commentsFound.isEmpty()) {

            return false;

        } else {

            return true;
        }
    }

    @Override
    public void postComment(WorkoutComment workoutComment) {

        int commentId = generateCommentId();

        String sqlQuery = "INSERT INTO workoutComment (commentId, userId, workoutId, workoutRate, " +
                "commentDateTimePosted, commentContent, commentStatus, postCommentAsGuest) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(sqlQuery, commentId, workoutComment.getUserId(), workoutComment.getWorkoutId(),
                workoutComment.getWorkoutRate(), LocalDateTime.now(), workoutComment.getCommentContent(),
                CommentStatus.OnWaiting.toString(), workoutComment.getPostCommentAsGuest());

    }

    @Override
    public List<WorkoutComment> findOnWaitingByWorkoutId(Long id) {

        String sqlQuery = "SELECT workoutComment.commentId, workoutComment.userId, workoutComment.workoutId, " +
                "workoutComment.workoutRate, " + "workoutComment.commentDateTimePosted, " +
                "workoutComment.commentContent, workoutComment.commentStatus, workoutComment.postCommentAsGuest, " +
                "registereduser.userName FROM workoutComment " +
                "INNER JOIN registereduser ON registereduser.userId = workoutComment.userId " +
                "WHERE commentStatus = 'OnWaiting';";

        return jdbcTemplate.query(sqlQuery, new WorkoutCommentRowMapper());

    }

    @Override
    public WorkoutComment findById(Long id) {

        String sqlQuery = "SELECT * FROM workoutcomment WHERE commentId = " + id + ";";

        List<WorkoutComment> workoutComment = jdbcTemplate.query(sqlQuery, new WorkoutCommentRowMapper());

        return workoutComment.get(0);
    }

    @Override
    public void updateCommentStatus(Long id, CommentStatus commentStatus) {

        String sqlQuery = "UPDATE workoutcomment SET commentStatus = ? WHERE commentId = ?;";

        jdbcTemplate.update(sqlQuery, commentStatus.toString(), id);
    }

    private int generateCommentId() {

        String sqlQuery = "SELECT * FROM workoutcomment WHERE commentId = (SELECT MAX(workoutcomment.commentId * 1) " +
                "FROM workoutcomment)";

        List<WorkoutComment> workoutComment = jdbcTemplate.query(sqlQuery, new WorkoutCommentRowMapper());

        int generatedCommentId;

        try {

            generatedCommentId = Math.toIntExact(workoutComment.get(0).getCommentId() + 1);


        } catch (Exception ignored) {

            generatedCommentId = 1;
        }

        return generatedCommentId;
    }
}
