package com.project.gymcenter.service;

import com.project.gymcenter.model.Period;
import com.project.gymcenter.model.ShoppingCart;

public interface ReservationService {

    public boolean checkReservationAvailability(Long shoppingCartId);

    public void reserveWorkout(ShoppingCart shoppingCartItem);

    public boolean checkReservationTimeOverlapping(ShoppingCart shoppingCart);

}
