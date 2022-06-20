package com.project.gymcenter.dao;

import com.project.gymcenter.model.PeriodReserved;
import com.project.gymcenter.model.ShoppingCart;

import java.util.List;

public interface ReservationDAO {

    public boolean checkReservationAvailability(Long shoppingCartId);

    public void reserveWorkout(ShoppingCart shoppingCartItem);

    public boolean checkReservationTimeOverlapping(ShoppingCart shoppingCart);

    public List<PeriodReserved> findAllByUserId(Long id);

    public void deleteById(Long id);

    public List<PeriodReserved> findAll();

    public List<PeriodReserved> find(String customerFilter, String reservationSortBy);
}
