package com.project.gymcenter.model;

import java.time.LocalDateTime;

public class ShoppingCart {

    private Long shoppingCartId;
    private Long periodId;
    private Long userId;

    private Long auditoriumId;
    private Long workoutId;
    private LocalDateTime workoutDateTimeStart;
    private LocalDateTime workoutDateTimeEnd;
    private String workoutName;
    private String workoutCoaches;
    private Double workoutPrice;
    private WorkoutOrganizatonType workoutOrganizationType;

    public ShoppingCart() {

        this.shoppingCartId = null;
        this.periodId = null;
        this.userId = null;

        this.auditoriumId = null;
        this.workoutId = null;
        this.workoutDateTimeStart = null;
        this.workoutDateTimeEnd = null;
        this.workoutName = null;
        this.workoutCoaches = null;
        this.workoutPrice = null;
        this.workoutOrganizationType = null;
    }

    public ShoppingCart(Long shoppingCartId, Long periodId, Long userId) {

        this.shoppingCartId = shoppingCartId;
        this.periodId = periodId;
        this.userId = userId;
    }

    public ShoppingCart(Long periodId, Long userId) {

        this.periodId = periodId;
        this.userId = userId;
    }

    public ShoppingCart(Long shoppingCartId, Long periodId, Long userId, Long auditoriumId, Long workoutId,
                        LocalDateTime workoutDateTimeStart, LocalDateTime workoutDateTimeEnd, String workoutName,
                        String workoutCoaches, Double workoutPrice, WorkoutOrganizatonType workoutOrganizationType) {

        this.shoppingCartId = shoppingCartId;
        this.periodId = periodId;
        this.userId = userId;
        this.auditoriumId = auditoriumId;
        this.workoutId = workoutId;
        this.workoutDateTimeStart = workoutDateTimeStart;
        this.workoutDateTimeEnd = workoutDateTimeEnd;
        this.workoutName = workoutName;
        this.workoutCoaches = workoutCoaches;
        this.workoutPrice = workoutPrice;
        this.workoutOrganizationType = workoutOrganizationType;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
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

    public WorkoutOrganizatonType getWorkoutOrganizationType() {
        return workoutOrganizationType;
    }

    public void setWorkoutOrganizationType(WorkoutOrganizatonType workoutOrganizationType) {
        this.workoutOrganizationType = workoutOrganizationType;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartId=" + shoppingCartId +
                ", periodId=" + periodId +
                ", userId=" + userId +
                ", auditoriumId=" + auditoriumId +
                ", workoutId=" + workoutId +
                ", workoutDateTimeStart=" + workoutDateTimeStart +
                ", workoutDateTimeEnd=" + workoutDateTimeEnd +
                ", workoutName='" + workoutName + '\'' +
                ", workoutCoaches='" + workoutCoaches + '\'' +
                ", workoutPrice=" + workoutPrice +
                ", workoutOrganizationType=" + workoutOrganizationType +
                '}';
    }
}
