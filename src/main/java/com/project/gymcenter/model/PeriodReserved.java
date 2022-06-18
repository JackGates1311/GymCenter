package com.project.gymcenter.model;

public class PeriodReserved {

    private Long periodId;
    private Long userId;

    private PeriodReserved() {

        this.periodId = null;
        this.userId = null;
    }

    public PeriodReserved(Long periodId, Long userId) {

        this.periodId = periodId;
        this.userId = userId;
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
}
