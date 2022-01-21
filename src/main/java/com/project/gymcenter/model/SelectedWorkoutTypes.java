package com.project.gymcenter.model;

public class SelectedWorkoutTypes {

    private String workoutTypeName;
    private String returnValue;

    public SelectedWorkoutTypes() {
        this.workoutTypeName = null;
        this.returnValue = null;
    }

    public SelectedWorkoutTypes(String workoutTypeName, String returnValue) {
        this.workoutTypeName = workoutTypeName;
        this.returnValue = returnValue;
    }

    public String getWorkoutTypeName() {
        return workoutTypeName;
    }

    public void setWorkoutTypeName(String workoutTypeName) {
        this.workoutTypeName = workoutTypeName;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String toString() {
        return "SelectedWorkoutTypes{" +
                "workoutTypeName='" + workoutTypeName + '\'' +
                ", returnValue='" + returnValue + '\'' +
                '}';
    }

}
