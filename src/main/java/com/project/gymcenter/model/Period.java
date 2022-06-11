package com.project.gymcenter.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class Period {

    private Long periodId;
    private String auditoriumId;
    private Long workoutId;
    private LocalDateTime workoutDateTimeStart;
    private LocalDateTime workoutDateTimeEnd;

    public Period() {

        this.periodId = null;
        this.auditoriumId = null;
        this.workoutId = null;
        this.workoutDateTimeStart = null;
        this.workoutDateTimeEnd = null;
    }

    public Period(Long periodId, String auditoriumId, Long workoutId, LocalDateTime workoutDateTimeStart,
                  LocalDateTime workoutDateTimeEnd) {

        this.periodId = periodId;
        this.auditoriumId = auditoriumId;
        this.workoutId = workoutId;
        this.workoutDateTimeStart = workoutDateTimeStart;
        this.workoutDateTimeEnd = workoutDateTimeEnd;

    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
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
