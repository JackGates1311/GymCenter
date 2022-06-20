package com.project.gymcenter.dao.form;

import com.project.gymcenter.model.WorkoutLevel;
import com.project.gymcenter.model.WorkoutOrganizationType;

import java.util.List;

public class AddEditWorkoutForm {

    private String newWorkoutName;
    private List<String> newWorkoutIncludedTypes;
    private String newWorkoutCoaches;
    private Double newWorkoutPrice;
    private WorkoutOrganizationType newWorkoutOrganizationType;
    private int newWorkoutLength;
    private WorkoutLevel newWorkoutLevel;
    private String newWorkoutDescription;
    private String newWorkoutImageUrl;

    public AddEditWorkoutForm() {

        super();
    }

    public AddEditWorkoutForm(String newWorkoutName, List<String> newWorkoutIncludedTypes, String newWorkoutCoaches,
                              Double newWorkoutPrice, WorkoutOrganizationType newWorkoutOrganizationType,
                              int newWorkoutLength, WorkoutLevel newWorkoutLevel, String newWorkoutImageUrl,
                              String newWorkoutDescription) {

        this.newWorkoutName = newWorkoutName;
        this.newWorkoutIncludedTypes = newWorkoutIncludedTypes;
        this.newWorkoutCoaches = newWorkoutCoaches;
        this.newWorkoutPrice = newWorkoutPrice;
        this.newWorkoutOrganizationType = newWorkoutOrganizationType;
        this.newWorkoutLength = newWorkoutLength;
        this.newWorkoutLevel = newWorkoutLevel;
        this.newWorkoutImageUrl = newWorkoutImageUrl;
        this.newWorkoutDescription = newWorkoutDescription;

    }

    public String getNewWorkoutName() {
        return newWorkoutName;
    }

    public void setNewWorkoutName(String newWorkoutName) {
        this.newWorkoutName = newWorkoutName;
    }

    public List<String> getNewWorkoutIncludedTypes() {
        return newWorkoutIncludedTypes;
    }

    public void setNewWorkoutIncludedTypes(List<String> newWorkoutIncludedTypes) {
        this.newWorkoutIncludedTypes = newWorkoutIncludedTypes;
    }

    public String getNewWorkoutCoaches() {
        return newWorkoutCoaches;
    }

    public void setNewWorkoutCoaches(String newWorkoutCoaches) {
        this.newWorkoutCoaches = newWorkoutCoaches;
    }

    public Double getNewWorkoutPrice() {
        return newWorkoutPrice;
    }

    public void setNewWorkoutPrice(Double newWorkoutPrice) {
        this.newWorkoutPrice = newWorkoutPrice;
    }

    public WorkoutOrganizationType getNewWorkoutOrganizationType() {
        return newWorkoutOrganizationType;
    }

    public void setNewWorkoutOrganizationType(WorkoutOrganizationType newWorkoutOrganizationType) {
        this.newWorkoutOrganizationType = newWorkoutOrganizationType;
    }

    public int getNewWorkoutLength() {
        return newWorkoutLength;
    }

    public void setNewWorkoutLength(int newWorkoutLength) {
        this.newWorkoutLength = newWorkoutLength;
    }

    public WorkoutLevel getNewWorkoutLevel() {
        return newWorkoutLevel;
    }

    public void setNewWorkoutLevel(WorkoutLevel newWorkoutLevel) {
        this.newWorkoutLevel = newWorkoutLevel;
    }

    public String getNewWorkoutImageUrl() {
        return newWorkoutImageUrl;
    }

    public void setNewWorkoutImageUrl(String newWorkoutImageUrl) {
        this.newWorkoutImageUrl = newWorkoutImageUrl;
    }

    public String getNewWorkoutDescription() {
        return newWorkoutDescription;
    }

    public void setNewWorkoutDescription(String newWorkoutDescription) {
        this.newWorkoutDescription = newWorkoutDescription;
    }

    @Override
    public String toString() {
        return "AddEditWorkoutForm{" +
                "newWorkoutName='" + newWorkoutName + '\'' +
                ", newWorkoutIncludedTypes=" + newWorkoutIncludedTypes +
                ", newWorkoutCoaches='" + newWorkoutCoaches + '\'' +
                ", newWorkoutPrice=" + newWorkoutPrice +
                ", newWorkoutOrganizationType=" + newWorkoutOrganizationType +
                ", newWorkoutLength=" + newWorkoutLength +
                ", newWorkoutLevel=" + newWorkoutLevel +
                ", newWorkoutDescription='" + newWorkoutDescription + '\'' +
                ", newWorkoutImageUrl='" + newWorkoutImageUrl + '\'' +
                '}';
    }
}
