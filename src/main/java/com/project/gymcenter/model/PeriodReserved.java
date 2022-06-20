package com.project.gymcenter.model;

import java.time.LocalDateTime;

public class PeriodReserved {

    private Long periodReservationId;
    private Long periodId;
    private Long userId;
    private LocalDateTime periodDateTimeReservation;

    private Long auditoriumId;
    private LocalDateTime workoutDateTimeStart;
    private LocalDateTime workoutDateTimeEnd;

    private Long workoutId;
    private String workoutName;
    private String workoutCoaches;
    private Double workoutPrice;
    private WorkoutOrganizationType workoutOrganizationType;

    private String userName;
    private UserRole userRole;

    private PeriodReserved() {

        this.periodReservationId = null;
        this.periodId = null;
        this.userId = null;
        this.periodDateTimeReservation = null;

        this.auditoriumId = null;
        this.workoutDateTimeStart = null;
        this.workoutDateTimeEnd = null;

        this.workoutId = null;
        this.workoutName = null;
        this.workoutCoaches = null;
        this.workoutPrice = null;
        this.workoutOrganizationType = null;

        this.userName = null;
        this.userRole = null;
    }

    public PeriodReserved(Long periodReservationId, Long periodId, Long userId,
                          LocalDateTime periodDateTimeReservation) {

        this.periodReservationId = periodReservationId;
        this.periodId = periodId;
        this.userId = userId;
        this.periodDateTimeReservation = periodDateTimeReservation;
    }

    public PeriodReserved(Long periodReservationId, Long periodId, Long userId, LocalDateTime periodDateTimeReservation,
                          Long auditoriumId, Long workoutId, LocalDateTime workoutDateTimeStart,
                          LocalDateTime workoutDateTimeEnd, String workoutName, String workoutCoaches,
                          Double workoutPrice, WorkoutOrganizationType workoutOrganizationType, String userName,
                          UserRole userRole) {

        this.periodReservationId = periodReservationId;
        this.periodId = periodId;
        this.userId = userId;
        this.periodDateTimeReservation = periodDateTimeReservation;

        this.auditoriumId = auditoriumId;
        this.workoutId = workoutId;
        this.workoutDateTimeStart = workoutDateTimeStart;
        this.workoutDateTimeEnd = workoutDateTimeEnd;
        this.workoutName = workoutName;
        this.workoutCoaches = workoutCoaches;
        this.workoutPrice = workoutPrice;
        this.workoutOrganizationType = workoutOrganizationType;

        this.userName = userName;
        this.userRole = userRole;
    }

    public Long getPeriodReservationId() {
        return periodReservationId;
    }

    public void setPeriodReservationId(Long periodReservationId) {
        this.periodReservationId = periodReservationId;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPeriodDateTimeReservation() {
        return periodDateTimeReservation;
    }

    public void setPeriodDateTimeReservation(LocalDateTime periodDateTimeReservation) {
        this.periodDateTimeReservation = periodDateTimeReservation;
    }

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public LocalDateTime getWorkoutDateTimeStart() {
        return workoutDateTimeStart;
    }

    public void setWorkoutDateTimeStart(LocalDateTime workoutDateTimeStart) {
        this.workoutDateTimeStart = workoutDateTimeStart;
    }

    public LocalDateTime getWorkoutDateTimeEnd() {
        return workoutDateTimeEnd;
    }

    public void setWorkoutDateTimeEnd(LocalDateTime workoutDateTimeEnd) {
        this.workoutDateTimeEnd = workoutDateTimeEnd;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutCoaches() {
        return workoutCoaches;
    }

    public void setWorkoutCoaches(String workoutCoaches) {
        this.workoutCoaches = workoutCoaches;
    }

    public Double getWorkoutPrice() {
        return workoutPrice;
    }

    public void setWorkoutPrice(Double workoutPrice) {
        this.workoutPrice = workoutPrice;
    }

    public WorkoutOrganizationType getWorkoutOrganizationType() {
        return workoutOrganizationType;
    }

    public void setWorkoutOrganizationType(WorkoutOrganizationType workoutOrganizationType) {
        this.workoutOrganizationType = workoutOrganizationType;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "PeriodReserved{" +
                "periodReservationId=" + periodReservationId +
                ", periodId=" + periodId +
                ", userId=" + userId +
                ", periodDateTimeReservation=" + periodDateTimeReservation +
                ", auditoriumId=" + auditoriumId +
                ", workoutDateTimeStart=" + workoutDateTimeStart +
                ", workoutDateTimeEnd=" + workoutDateTimeEnd +
                ", workoutId=" + workoutId +
                ", workoutName='" + workoutName + '\'' +
                ", workoutCoaches='" + workoutCoaches + '\'' +
                ", workoutPrice=" + workoutPrice +
                ", workoutOrganizationType=" + workoutOrganizationType +
                ", userName='" + userName + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
