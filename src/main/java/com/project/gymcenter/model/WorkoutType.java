package com.project.gymcenter.model;

public class WorkoutType {

    private String workoutTypeName;
    private String workoutTypeDetailedDescription;
    private String workoutImage;

    public WorkoutType() {

        this.workoutTypeName = null;
        this.workoutTypeDetailedDescription = null;
        this.workoutImage = null;

    }

    public WorkoutType(String workoutTypeName, String workoutTypeDetailedDescription, String workoutImage) {

        this.workoutTypeName = workoutTypeName;
        this.workoutTypeDetailedDescription = workoutTypeDetailedDescription;
        this.workoutImage = workoutImage;

    }

    public String getWorkoutTypeName() {
        return workoutTypeName;
    }

    public void setWorkoutTypeName(String workoutTypeName) {
        this.workoutTypeName = workoutTypeName;
    }

    public String getWorkoutTypeDetailedDescription() {
        return workoutTypeDetailedDescription;
    }

    public void setWorkoutTypeDetailedDescription(String workoutTypeDetailedDescription) {
        this.workoutTypeDetailedDescription = workoutTypeDetailedDescription;
    }

    public String getWorkoutImage() {
        return workoutImage;
    }

    public void setWorkoutImage(String workoutImage) {
        this.workoutImage = workoutImage;
    }

    @Override
    public String toString() {
        return "WorkoutType{" + "\n" +
                "workoutTypeName='" + workoutTypeName + '\'' + "\n" +
                ", workoutTypeDetailedDescription='" + workoutTypeDetailedDescription + '\'' + "\n" +
                ", workoutImage='" + workoutImage + '\'' + "\n" +
                '}';
    }
}
