package com.project.gymcenter.dao.form;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class AddNewPeriodForm {

    private Long workoutId;
    private String auditoriumId;
    private String periodDateTimeStart;

    public AddNewPeriodForm() {

        super();

    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(String auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public String getPeriodDateTimeStart() {

        return periodDateTimeStart;
    }

    public void setPeriodDateTimeStart(String periodDateTimeStart) {
        this.periodDateTimeStart = periodDateTimeStart;
    }
}
