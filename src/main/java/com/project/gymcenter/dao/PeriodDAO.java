package com.project.gymcenter.dao;

import com.project.gymcenter.model.Period;

public interface PeriodDAO {

    public void add(Period period);

    public Boolean checkForPeriodAvailability(Period period);
}
