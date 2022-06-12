package com.project.gymcenter.dao;

import com.project.gymcenter.model.Period;

import java.util.List;

public interface PeriodDAO {

    public void add(Period period);

    public Boolean checkForPeriodAvailability(Period period);

    public List<Period> findAvailablePeriodsByWorkoutId(Long id);

    public int generatePeriodId();

    public List<Period> findAllAvailablePeriods();
}
