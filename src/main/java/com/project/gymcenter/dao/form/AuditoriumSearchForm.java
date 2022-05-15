package com.project.gymcenter.dao.form;

public class AuditoriumSearchForm {

    private String auditoriumId;
    private String auditoriumSortBy;

    public AuditoriumSearchForm() {

        super();

    }

    public String getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(String auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public String getAuditoriumSortBy() {
        return auditoriumSortBy;
    }

    public void setAuditoriumSortBy(String auditoriumSortBy) {
        this.auditoriumSortBy = auditoriumSortBy;
    }
}
