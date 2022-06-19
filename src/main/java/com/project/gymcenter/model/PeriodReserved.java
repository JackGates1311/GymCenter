package com.project.gymcenter.model;

import java.time.LocalDateTime;

public class PeriodReserved {

    private Long periodReservationId;
    private Long periodId;
    private Long userId;
    private LocalDateTime periodDateTimeReservation;

    private PeriodReserved() {

        this.periodReservationId = null;
        this.periodId = null;
        this.userId = null;
        this.periodDateTimeReservation = null;
    }

    public PeriodReserved(Long periodReservationId, Long periodId, Long userId,
                          LocalDateTime periodDateTimeReservation) {

        this.periodReservationId = periodReservationId;
        this.periodId = periodId;
        this.userId = userId;
        this.periodDateTimeReservation = periodDateTimeReservation;
    }

    public Long getPeriodReservationId() {
        return periodReservationId;
    }

    public void setPeriodReservationId(Long periodReservationId) {
        this.periodReservationId = periodReservationId;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPeriodDateTimeReservation() {
        return periodDateTimeReservation;
    }

    public void setPeriodDateTimeReservation(LocalDateTime periodDateTimeReservation) {
        this.periodDateTimeReservation = periodDateTimeReservation;
    }

    @Override
    public String toString() {
        return "PeriodReserved{" +
                "periodReservationId=" + periodReservationId +
                ", periodId=" + periodId +
                ", userId=" + userId +
                ", periodDateTimeReservation=" + periodDateTimeReservation +
                '}';
    }
}
