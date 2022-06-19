package com.project.gymcenter.model;

public class WishList {

    private Long wishListId;
    private Long workoutId;
    private Long userId;
    private String workoutName;

    public WishList() {

        this.wishListId = null;
        this.workoutId = null;
        this.userId = null;
        this.workoutName = null;
    }

    public WishList(Long wishListId, Long workoutId, Long userId, String workoutName) {

        this.wishListId = wishListId;
        this.workoutId = workoutId;
        this.userId = userId;
        this.workoutName = workoutName;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
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

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "wishListId=" + wishListId +
                ", workoutId=" + workoutId +
                ", userId=" + userId +
                ", workoutName='" + workoutName + '\'' +
                '}';
    }
}
