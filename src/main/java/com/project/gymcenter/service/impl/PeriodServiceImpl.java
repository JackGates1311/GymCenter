package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.PeriodDAO;
import com.project.gymcenter.model.Period;
import com.project.gymcenter.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    public PeriodDAO periodDAO;

    @Override
    public void add(Period period) {

        periodDAO.add(period);
    }

    @Override
    public Boolean checkForPeriodAvailability(Period period) {

        return periodDAO.checkForPeriodAvailability(period);
    }

    @Override
    public List<Period> findAvailablePeriodsByWorkoutId(Long id, Long userId) {
        
        return periodDAO.findAvailablePeriodsByWorkoutId(id, userId);
    }

    @Override
    public List<Period> findAllAvailablePeriods(Long userId) {

        return periodDAO.findAllAvailablePeriods(userId);
    }

    @Override
    public Period findByPeriodId(Long periodId) {

        return periodDAO.findByPeriodId(periodId);
    }

    @Override
    public boolean checkForPeriodAvailability(Long periodId) {

        return periodDAO.checkForPeriodAvailability(periodId);
    }

    @Override
    public void setCapacityFullByPeriodId(Long periodId) {

        periodDAO.setCapacityFullByPeriodId(periodId);
    }
}
