package com.project.gymcenter.model;

public class Workout {

    private Long workoutId;
    private String workoutTypeName;
    private String workoutCoaches;
    private String workoutDescription;
    private Double workoutPrice;
    private WorkoutOrganizatonType workoutOrganizationType;
    private WorkoutLevel workoutLevel;
    private int workoutLength;
    private Double workoutAverageRate;
    private Boolean isDeleted;
    private String workoutName;
    private String workoutImage;

    public Workout() {

        this.workoutId = null;
        this.workoutTypeName = null;
        this.workoutCoaches = null;
        this.workoutDescription = null;
        this.workoutPrice = null;
        this.workoutOrganizationType = null;
        this.workoutLength = 0;
        this.workoutAverageRate = null;
        this.isDeleted = null;
        this.workoutName = null;
        this.workoutImage = null;

    }

    public Workout(Long workoutId, String workoutTypeName, String workoutCoaches, String workoutDescription,
                   Double workoutPrice, WorkoutOrganizatonType workoutOrganizationType, WorkoutLevel workoutLevel,
                   int workoutLength, Double workoutAverageRate, Boolean isDeleted, String workoutName,
                   String workoutImage) {

        super();

        this.workoutId = workoutId;
        this.workoutTypeName = workoutTypeName;
        this.workoutCoaches = workoutCoaches;
        this.workoutDescription = workoutDescription;
        this.workoutPrice = workoutPrice;
        this.workoutOrganizationType = workoutOrganizationType;
        this.workoutLevel = workoutLevel;
        this.workoutLength = workoutLength;
        this.workoutAverageRate = workoutAverageRate;
        this.isDeleted = isDeleted;
        this.workoutName = workoutName;
        this.workoutImage = workoutImage;
    }

    public Workout(String workoutTypeName, String newWorkoutCoaches, String newWorkoutDescription,
                   Double newWorkoutPrice, WorkoutOrganizatonType newWorkoutOrganizationType,
                   WorkoutLevel newWorkoutLevel, int newWorkoutLength, String newWorkoutName,
                   String newWorkoutImage) {

        this.workoutTypeName = workoutTypeName;
        this.workoutCoaches = newWorkoutCoaches;
        this.workoutDescription = newWorkoutDescription;
        this.workoutPrice = newWorkoutPrice;
        this.workoutOrganizationType = newWorkoutOrganizationType;
        this.workoutLevel = newWorkoutLevel;
        this.workoutLength = newWorkoutLength;
        this.workoutName = newWorkoutName;
        this.workoutImage = newWorkoutImage;

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

    public WorkoutOrganizatonType getWorkoutOrganizationType() {
        return workoutOrganizationType;
    }

    public void setWorkoutOrganizationType(WorkoutOrganizatonType workoutOrganizationType) {
        this.workoutOrganizationType = workoutOrganizationType;
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

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutImage() {
        return workoutImage;
    }

    public void setWorkoutImage(String workoutImage) {
        this.workoutImage = workoutImage;
    }

    @Override
    public String toString() {
        return "Workout{" + "\n" +
                "workoutId=" + workoutId + "\n" +
                ", workoutTypeName='" + workoutTypeName + '\'' + "\n" +
                ", workoutCoaches='" + workoutCoaches + '\'' + "\n" +
                ", workoutDescription='" + workoutDescription + '\'' + "\n" +
                ", workoutPrice=" + workoutPrice + "\n" +
                ", workoutOrganizatonType=" + workoutOrganizationType + "\n" +
                ", workoutLevel=" + workoutLevel + "\n" +
                ", workoutLength=" + workoutLength + "\n" +
                ", workoutAverageRate=" + workoutAverageRate + "\n" +
                ", isDeleted=" + isDeleted + "\n" +
                ", workoutName=" + workoutName + "\n" +
                ", workoutImage=" + workoutImage + "\n" +
                '}';
    }

}
