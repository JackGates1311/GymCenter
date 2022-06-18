package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.ReservationDAO;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationDAO reservationDAO;

    @Override
    public boolean checkReservationAvailability(Long shoppingCartId) {

        return reservationDAO.checkReservationAvailability(shoppingCartId);
    }

    @Override
    public void reserveWorkout(ShoppingCart shoppingCartItem) {

        reservationDAO.reserveWorkout(shoppingCartItem);
    }

    @Override
    public boolean checkReservationTimeOverlapping(ShoppingCart shoppingCart) {

        return reservationDAO.checkReservationTimeOverlapping(shoppingCart);
    }
}
