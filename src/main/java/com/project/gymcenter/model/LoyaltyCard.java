package com.project.gymcenter.model;

public class LoyaltyCard {

    private Long loyaltyCardId;
    private Long workoutId;
    private Long userId;
    private Integer discount;
    private Integer points;
    private Boolean isApproved;

    public LoyaltyCard() {

        this.loyaltyCardId = null;
        this.workoutId = null;
        this.userId = null;
        this.discount = null;
        this.points = null;
        this.isApproved = null;
    }

    public LoyaltyCard(Long loyaltyCardId, Long workoutId, Long userId, Integer discount, Integer points, Boolean isApproved) {

        this.loyaltyCardId = loyaltyCardId;
        this.workoutId = workoutId;
        this.userId = userId;
        this.discount = discount;
        this.points = points;
        this.isApproved = isApproved;
    }

    public Long getLoyaltyCardId() {
        return loyaltyCardId;
    }

    public void setLoyaltyCardId(Long loyaltyCardId) {
        this.loyaltyCardId = loyaltyCardId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    @Override
    public String toString() {
        return "LoyaltyCard{" +
                "loyaltyCardId=" + loyaltyCardId +
                ", workoutId=" + workoutId +
                ", userId=" + userId +
                ", discount=" + discount +
                ", points=" + points +
                ", isApproved=" + isApproved +
                '}';
    }
}
