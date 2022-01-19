package com.project.gymcenter.model;

public class WorkoutIncludedTypes {

    public Long workoutId;
    public String workoutTypeName;

    public WorkoutIncludedTypes() {

        workoutId = null;
        workoutTypeName = null;

    }

    public WorkoutIncludedTypes(Long workoutId, String workoutTypeName) {

        this.workoutId = workoutId;
        this.workoutTypeName = workoutTypeName;

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

    @Override
    public String toString() {
        return "WorkoutIncludedTypes{" +  "\n" +
                "workoutId=" + workoutId +  "\n" +
                ", workoutTypeName='" + workoutTypeName + '\'' +  "\n" +
                '}';
    }
}
