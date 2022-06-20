package com.project.gymcenter.service;

import com.project.gymcenter.model.Period;
import com.project.gymcenter.model.PeriodReserved;
import com.project.gymcenter.model.ShoppingCart;

import java.util.List;

public interface ReservationService {

    public boolean checkReservationAvailability(Long shoppingCartId);

    public void reserveWorkout(ShoppingCart shoppingCartItem);

    public boolean checkReservationTimeOverlapping(ShoppingCart shoppingCart);

    public List<PeriodReserved> findAllByUserId(Long id);

    public List<PeriodReserved> findAll();

    public void deleteById(Long id);

    public List<PeriodReserved> find(String customerFilter, String reservationSortBy);
}
