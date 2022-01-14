package com.project.gymcenter.model;

public class Workout {

    private Long workoutId;
    private String workoutTypeName;
    private String workoutCoaches;
    private String workoutDescription;
    private Double workoutPrice;
    private WorkoutOrganizatonType workoutOrganizatonType;
    private WorkoutLevel workoutLevel;
    private int workoutLength;
    private Double workoutAverageRate;
    private Boolean isDeleted;

    public Workout() {

        this.workoutId = null;
        this.workoutTypeName = null;
        this.workoutCoaches = null;
        this.workoutDescription = null;
        this.workoutPrice = null;
        this.workoutOrganizatonType = null;
        this.workoutLength = 0;
        this.workoutAverageRate = null;
        this.isDeleted = null;

    }

    public Workout(Long workoutId, String workoutTypeName, String workoutCoaches, String workoutDescription,
                   Double workoutPrice, WorkoutOrganizatonType workoutOrganizatonType, WorkoutLevel workoutLevel,
                   int workoutLength, Double workoutAverageRate, Boolean isDeleted) {

        super();

        this.workoutId = workoutId;
        this.workoutTypeName = workoutTypeName;
        this.workoutCoaches = workoutCoaches;
        this.workoutDescription = workoutDescription;
        this.workoutPrice = workoutPrice;
        this.workoutOrganizatonType = workoutOrganizatonType;
        this.workoutLevel = workoutLevel;
        this.workoutLength = workoutLength;
        this.workoutAverageRate = workoutAverageRate;
        this.isDeleted = isDeleted;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutTypeName() {
        return workoutTypeName;
    }

    public void setWorkoutTypeName(String workoutTypeName) {
        this.workoutTypeName = workoutTypeName;
    }

    public String getWorkoutCoaches() {
        return workoutCoaches;
    }

    public void setWorkoutCoaches(String workoutCoaches) {
        this.workoutCoaches = workoutCoaches;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public Double getWorkoutPrice() {
        return workoutPrice;
    }

    public void setWorkoutPrice(Double workoutPrice) {
        this.workoutPrice = workoutPrice;
    }

    public WorkoutOrganizatonType getWorkoutOrganizatonType() {
        return workoutOrganizatonType;
    }

    public void setWorkoutOrganizatonType(WorkoutOrganizatonType workoutOrganizatonType) {
        this.workoutOrganizatonType = workoutOrganizatonType;
    }

    public WorkoutLevel getWorkoutLevel() {
        return workoutLevel;
    }

    public void setWorkoutLevel(WorkoutLevel workoutLevel) {
        this.workoutLevel = workoutLevel;
    }

    public int getWorkoutLength() {
        return workoutLength;
    }

    public void setWorkoutLength(int workoutLength) {
        this.workoutLength = workoutLength;
    }

    public Double getWorkoutAverageRate() {
        return workoutAverageRate;
    }

    public void setWorkoutAverageRate(Double workoutAverageRate) {
        this.workoutAverageRate = workoutAverageRate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", workoutTypeName='" + workoutTypeName + '\'' +
                ", workoutCoaches='" + workoutCoaches + '\'' +
                ", workoutDescription='" + workoutDescription + '\'' +
                ", workoutPrice=" + workoutPrice +
                ", workoutOrganizatonType=" + workoutOrganizatonType +
                ", workoutLevel=" + workoutLevel +
                ", workoutLength=" + workoutLength +
                ", workoutAverageRate=" + workoutAverageRate +
                ", isDeleted=" + isDeleted +
                '}';
    }

}
