package com.project.gymcenter.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class Period {

    private String auditoriumId;
    private Long workoutId;
    private LocalDateTime workoutDateTimeStart;
    private LocalDateTime workoutDateTimeEnd;

    public Period() {

        this.auditoriumId = null;
        this.workoutId = null;
        this.workoutDateTimeStart = null;
        this.workoutDateTimeEnd = null;
    }

    public Period(String auditoriumId, Long workoutId, LocalDateTime workoutDateTimeStart,
                  LocalDateTime workoutDateTimeEnd) {

        this.auditoriumId = auditoriumId;
        this.workoutId = workoutId;
        this.workoutDateTimeStart = workoutDateTimeStart;
        this.workoutDateTimeEnd = workoutDateTimeEnd;

    }

    public String getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(String auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public LocalDateTime getWorkoutDateTimeStart() {
        return workoutDateTimeStart;
    }

    public void setWorkoutDateTimeStart(LocalDateTime workoutDateTimeStart) {
        this.workoutDateTimeStart = workoutDateTimeStart;
    }

    public LocalDateTime getWorkoutDateTimeEnd() {
        return workoutDateTimeEnd;
    }

    public void setWorkoutDateTimeEnd(LocalDateTime workoutDateTimeEnd) {
        this.workoutDateTimeEnd = workoutDateTimeEnd;
    }

    @Override
    public String toString() {
        return "Period{" +
                "auditoriumId='" + auditoriumId + '\'' +
                ", workoutId=" + workoutId +
                ", workoutDateTimeStart=" + workoutDateTimeStart +
                ", workoutDateTimeEnd=" + workoutDateTimeEnd +
                '}';
    }

}
