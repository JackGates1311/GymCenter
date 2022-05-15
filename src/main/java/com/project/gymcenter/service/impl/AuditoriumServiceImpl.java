package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.AuditoriumDAO;
import com.project.gymcenter.model.Auditorium;
import com.project.gymcenter.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    public AuditoriumDAO auditoriumDAO;

    @Override
    public List<Auditorium> findAll() {

        return auditoriumDAO.findAll();
    }

    @Override
    public List<Auditorium> find(String auditoriumId, String auditoriumSortBy) {

        return auditoriumDAO.find(auditoriumId, auditoriumSortBy);
    }

    @Override
    public void update(Auditorium auditorium) {

        auditoriumDAO.update(auditorium);
    }

    @Override
    public int remove(String id) {

        return auditoriumDAO.remove(id);
    }

    @Override
    public void add(Auditorium auditorium) {

        auditoriumDAO.add(auditorium);
    }
}
