package com.project.gymcenter.dao.form;

public class AddEditAuditoriumForm {

    private String auditoriumId;
    private Long auditoriumCapacity;

    public AddEditAuditoriumForm() {

        super();

    }

    public String getAuditoriumId() {

        return auditoriumId;
    }

    public void setAuditoriumId(String auditoriumId) {

        this.auditoriumId = auditoriumId;
    }

    public Long getAuditoriumCapacity() {

        return auditoriumCapacity;
    }

    public void setAuditoriumCapacity(Long auditoriumCapacity) {

        this.auditoriumCapacity = auditoriumCapacity;
    }

}
