package com.project.gymcenter.dao;

import com.project.gymcenter.model.ShoppingCart;

public interface ReservationDAO {

    public boolean checkReservationAvailability(Long shoppingCartId);

    public void reserveWorkout(ShoppingCart shoppingCartItem);

    public boolean checkReservationTimeOverlapping(ShoppingCart shoppingCart);
}
