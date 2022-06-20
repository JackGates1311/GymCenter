package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.ReservationDAO;
import com.project.gymcenter.model.PeriodReserved;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PeriodReserved> findAllByUserId(Long id) {

        return reservationDAO.findAllByUserId(id);
    }

    @Override
    public List<PeriodReserved> findAll() {

        return reservationDAO.findAll();
    }

    @Override
    public void deleteById(Long id) {

        reservationDAO.deleteById(id);
    }

    @Override
    public List<PeriodReserved> find(String customerFilter, String reservationSortBy) {

        return reservationDAO.find(customerFilter, reservationSortBy);
    }
}
