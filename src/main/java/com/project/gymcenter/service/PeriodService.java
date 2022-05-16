package com.project.gymcenter.service;

import com.project.gymcenter.model.Period;
import org.springframework.stereotype.Service;

public interface PeriodService {

    public void add(Period period);

    public Boolean checkForPeriodAvailability(Period period);

}
