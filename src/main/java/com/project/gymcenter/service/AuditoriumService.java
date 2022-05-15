package com.project.gymcenter.service;

import com.project.gymcenter.model.Auditorium;

import java.util.List;

public interface AuditoriumService {

    public List<Auditorium> findAll();

    public List<Auditorium> find(String auditoriumId , String auditoriumSortBy);

    public void update(Auditorium auditorium);

    public int remove(String id);

    public void add(Auditorium auditorium);
}
