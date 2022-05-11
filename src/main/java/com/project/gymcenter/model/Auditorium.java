package com.project.gymcenter.model;

public class Auditorium {

    private String auditoriumId;
    private Long capacity;
    private Boolean isDeleted;

    public Auditorium() {

        this.auditoriumId = null;
        this.capacity = null;
        this.isDeleted = null;

    }

    public Auditorium(String auditoriumId, Long capacity, Boolean isDeleted) {

        this.auditoriumId = auditoriumId;
        this.capacity = capacity;
        this.isDeleted = isDeleted;

    }

    public String getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(String auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "auditoriumId='" + auditoriumId + '\'' +
                ", capacity=" + capacity +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
