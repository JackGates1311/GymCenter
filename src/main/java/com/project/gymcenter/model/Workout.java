package com.project.gymcenter.model;

public class Workout {

    private Long workoutId;
    private String workoutTypeName;
    private String workoutCoaches;
    private String workoutDescription;
    private Double workoutPrice;
    private WorkoutOrganizationType workoutOrganizationType;
    private WorkoutLevel workoutLevel;
    private int workoutLength;
    private Double workoutAverageRate;
    private Boolean isDeleted;
    private String workoutName;
    private String workoutImage;

    private Long reservationCount;
    private Double salary;

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

        this.reservationCount = null;
        this.salary = null;

    }

    public Workout(Long workoutId, String workoutTypeName, String workoutCoaches, String workoutDescription,
                   Double workoutPrice, WorkoutOrganizationType workoutOrganizationType, WorkoutLevel workoutLevel,
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
                   Double newWorkoutPrice, WorkoutOrganizationType newWorkoutOrganizationType,
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

    public Workout(Long workoutId, String workoutName, String workoutCoaches, Long reservationCount, Double salary) {

        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.workoutCoaches = workoutCoaches;
        this.reservationCount = reservationCount;
        this.salary = salary;
    }

    public Workout(Long workoutId, Long reservationCount) {

        this.workoutId = workoutId;
        this.reservationCount = reservationCount;
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

    public WorkoutOrganizationType getWorkoutOrganizationType() {
        return workoutOrganizationType;
    }

    public void setWorkoutOrganizationType(WorkoutOrganizationType workoutOrganizationType) {
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

    public Long getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Long reservationCount) {
        this.reservationCount = reservationCount;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", workoutTypeName='" + workoutTypeName + '\'' +
                ", workoutCoaches='" + workoutCoaches + '\'' +
                ", workoutDescription='" + workoutDescription + '\'' +
                ", workoutPrice=" + workoutPrice +
                ", workoutOrganizationType=" + workoutOrganizationType +
                ", workoutLevel=" + workoutLevel +
                ", workoutLength=" + workoutLength +
                ", workoutAverageRate=" + workoutAverageRate +
                ", isDeleted=" + isDeleted +
                ", workoutName='" + workoutName + '\'' +
                ", workoutImage='" + workoutImage + '\'' +
                ", reservationCount=" + reservationCount +
                ", salary=" + salary +
                '}';
    }

}
