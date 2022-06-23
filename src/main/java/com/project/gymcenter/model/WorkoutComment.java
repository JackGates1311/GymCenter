package com.project.gymcenter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkoutComment {

    private Long commentId;
    private Long userId;
    private Long workoutId;
    private Double workoutRate;
    private LocalDateTime commentDateTimePosted;
    private String commentContent;
    private CommentStatus commentStatus;
    private Boolean postCommentAsGuest;
    private String userName;

    public WorkoutComment() {

        this.commentId = null;
        this.userId = null;
        this.workoutId = null;
        this.workoutRate = null;
        this.commentDateTimePosted = null;
        this.commentContent = null;
        this.commentStatus = null;
        this.postCommentAsGuest = null;
        this.userName = null;
    }

    public WorkoutComment(Long commentId, Long userId, Long workoutId, Double workoutRate,
                          LocalDateTime commentDateTimePosted, String commentContent, CommentStatus commentStatus,
                          Boolean postCommentAsGuest, String userName) {

        this.commentId = commentId;
        this.userId = userId;
        this.workoutId = workoutId;
        this.workoutRate = workoutRate;
        this.commentDateTimePosted = commentDateTimePosted;
        this.commentContent = commentContent;
        this.postCommentAsGuest = postCommentAsGuest;
        this.userName = userName;
        this.commentStatus = commentStatus;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Double getWorkoutRate() {
        return workoutRate;
    }

    public void setWorkoutRate(Double workoutRate) {
        this.workoutRate = workoutRate;
    }

    public LocalDateTime getCommentDateTimePosted() {
        return commentDateTimePosted;
    }

    public void setCommentDateTimePosted(LocalDateTime commentDateTimePosted) {
        this.commentDateTimePosted = commentDateTimePosted;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Boolean getPostCommentAsGuest() {
        return postCommentAsGuest;
    }

    public void setPostCommentAsGuest(Boolean postCommentAsGuest) {
        this.postCommentAsGuest = postCommentAsGuest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "WorkoutComment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", workoutId=" + workoutId +
                ", workoutRate=" + workoutRate +
                ", commentDateTimePosted=" + commentDateTimePosted +
                ", commentContent='" + commentContent + '\'' +
                ", commentStatus=" + commentStatus +
                ", postCommentAsGuest=" + postCommentAsGuest +
                ", userName='" + userName + '\'' +
                '}';
    }
}
