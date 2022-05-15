package com.project.gymcenter.dao;

import com.project.gymcenter.model.Auditorium;

import java.util.List;

public interface AuditoriumDAO {

    public List<Auditorium> findAll();

    public List<Auditorium> find(String auditoriumId, String auditoriumSortBy);

    public void update(Auditorium auditorium);

    public int remove(String id);

    public Boolean add(Auditorium auditorium);
}
