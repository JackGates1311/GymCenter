package com.project.gymcenter.form;

import com.project.gymcenter.model.WorkoutLevel;
import com.project.gymcenter.model.WorkoutOrganizatonType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AddNewWorkoutForm {

    private String newWorkoutName;
    private List<String> newWorkoutIncludedTypes;
    private String newWorkoutCoaches;
    private Double newWorkoutPrice;
    private WorkoutOrganizatonType newWorkoutOrganizationType;
    private int newWorkoutLength;
    private WorkoutLevel newWorkoutLevel;
    private MultipartFile newWorkoutImage;
    private String newWorkoutDescription;


    public AddNewWorkoutForm() {

        super();
    }

    public AddNewWorkoutForm(String newWorkoutName, List<String> newWorkoutIncludedTypes, String newWorkoutCoaches,
                             Double newWorkoutPrice, WorkoutOrganizatonType newWorkoutOrganizationType,
                             int newWorkoutLength, WorkoutLevel newWorkoutLevel, MultipartFile newWorkoutImage,
                             String newWorkoutDescription) {

        this.newWorkoutName = newWorkoutName;
        this.newWorkoutIncludedTypes = newWorkoutIncludedTypes;
        this.newWorkoutCoaches = newWorkoutCoaches;
        this.newWorkoutPrice = newWorkoutPrice;
        this.newWorkoutOrganizationType = newWorkoutOrganizationType;
        this.newWorkoutLength = newWorkoutLength;
        this.newWorkoutLevel = newWorkoutLevel;
        this.newWorkoutImage = newWorkoutImage;
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

    public WorkoutOrganizatonType getNewWorkoutOrganizationType() {
        return newWorkoutOrganizationType;
    }

    public void setNewWorkoutOrganizationType(WorkoutOrganizatonType newWorkoutOrganizationType) {
        this.newWorkoutOrganizationType = newWorkoutOrganizationType;
    }

    public int getnewWorkoutLength() {
        return newWorkoutLength;
    }

    public void setnewWorkoutLength(int newWorkoutLength) {
        this.newWorkoutLength = newWorkoutLength;
    }

    public WorkoutLevel getNewWorkoutLevel() {
        return newWorkoutLevel;
    }

    public void setNewWorkoutLevel(WorkoutLevel newWorkoutLevel) {
        this.newWorkoutLevel = newWorkoutLevel;
    }

    public MultipartFile getNewWorkoutImage() {
        return newWorkoutImage;
    }

    public void setNewWorkoutImage(MultipartFile newWorkoutImage) {
        this.newWorkoutImage = newWorkoutImage;
    }

    public String getNewWorkoutDescription() {
        return newWorkoutDescription;
    }

    public void setNewWorkoutDescription(String newWorkoutDescription) {
        this.newWorkoutDescription = newWorkoutDescription;
    }

    @Override
    public String toString() {
        return "AddNewWorkoutForm{" +
                "newWorkoutName='" + newWorkoutName + '\'' +
                ", newWorkoutIncludedTypes=" + newWorkoutIncludedTypes +
                ", workoutCoaches='" + newWorkoutCoaches + '\'' +
                ", newWorkoutPrice=" + newWorkoutPrice +
                ", newWorkoutOrganizatonType=" + newWorkoutOrganizationType +
                ", newWorkoutLength=" + newWorkoutLength +
                ", newWorkoutLevel=" + newWorkoutLevel +
                ", newWorkoutImage='" + newWorkoutImage + '\'' +
                ", newWorkoutDescription='" + newWorkoutDescription + '\'' +
                '}';
    }
}
