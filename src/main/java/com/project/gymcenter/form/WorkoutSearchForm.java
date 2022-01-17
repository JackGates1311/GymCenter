package com.project.gymcenter.form;

public class WorkoutSearchForm {

    private String workoutName;
    private String workoutCoach;
    private Double workoutPriceMin;
    private Double workoutPriceMax;
    private String workoutTypeName;
    private String workoutOrganizationType;
    private String workoutLevel;
    private String workoutSortBy;

    public WorkoutSearchForm() {

        super();
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutCoach() {
        return workoutCoach;
    }

    public void setWorkoutCoach(String workoutCoach) {
        this.workoutCoach = workoutCoach;
    }

    public Double getWorkoutPriceMin() {
        return workoutPriceMin;
    }

    public void setWorkoutPriceMin(Double workoutPriceMin) {
        this.workoutPriceMin = workoutPriceMin;
    }

    public Double getWorkoutPriceMax() {
        return workoutPriceMax;
    }

    public void setWorkoutPriceMax(Double workoutPriceMax) {
        this.workoutPriceMax = workoutPriceMax;
    }

    public String getWorkoutTypeName() {
        return workoutTypeName;
    }

    public void setWorkoutTypeName(String workoutType) {
        this.workoutTypeName = workoutType;
    }

    public String getWorkoutOrganizationType() {
        return workoutOrganizationType;
    }

    public void setWorkoutOrganizationType(String workoutOrganizationType) {
        this.workoutOrganizationType = workoutOrganizationType;
    }

    public String getWorkoutLevel() {
        return workoutLevel;
    }

    public void setWorkoutLevel(String workoutLevel) {
        this.workoutLevel = workoutLevel;
    }

    public String getWorkoutSortBy() {
        return workoutSortBy;
    }

    public void setWorkoutSortBy(String workoutSortBy) {
        this.workoutSortBy = workoutSortBy;
    }

}
