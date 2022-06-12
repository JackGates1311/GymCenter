package com.project.gymcenter.service;

import com.project.gymcenter.model.Period;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PeriodService {

    public void add(Period period);

    public Boolean checkForPeriodAvailability(Period period);

    public List<Period> findAvailablePeriodsByWorkoutId(Long id);

    public List<Period> findAllAvailablePeriods();

}
