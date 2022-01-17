package com.project.gymcenter.model;

public class WorkoutType {

    private String workoutTypeName;
    private String workoutTypeDetailedDescription;

    public WorkoutType() {

        this.workoutTypeName = null;
        this.workoutTypeDetailedDescription = null;

    }

    public WorkoutType(String workoutTypeName, String workoutTypeDetailedDescription) {

        this.workoutTypeName = workoutTypeName;
        this.workoutTypeDetailedDescription = workoutTypeDetailedDescription;

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

    @Override
    public String toString() {
        return "WorkoutType{" + "\n" +
                "workoutTypeName='" + workoutTypeName + '\'' + "\n" +
                ", workoutTypeDetailedDescription='" + workoutTypeDetailedDescription + '\'' + "\n" +
                '}';
    }
}
