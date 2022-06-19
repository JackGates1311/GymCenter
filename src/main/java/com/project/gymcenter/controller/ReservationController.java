package com.project.gymcenter.controller;

import com.project.gymcenter.model.Period;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.PeriodService;
import com.project.gymcenter.service.ReservationService;
import com.project.gymcenter.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    PeriodService periodService;

    NavBarController navBarController = new NavBarController();

    @RequestMapping(value = "/reserveWorkout")
    public String reserveWorkout(@RequestParam Long id, HttpServletRequest request, Model model) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            ShoppingCart shoppingCartItem = shoppingCartService.findPeriodByShoppingCartId(id);

            boolean checkReservationAvailability;

            if(shoppingCartItem.getCapacityFull().equals(true)) {

                checkReservationAvailability = false;

            } else {

                checkReservationAvailability = reservationService.checkReservationAvailability(id);
            }

            boolean checkForPeriodAvailability = periodService.checkForPeriodAvailability(
                    shoppingCartItem.getPeriodId());

            boolean checkReservationTimeOverlapping = reservationService.checkReservationTimeOverlapping(
                    shoppingCartItem);

            if(checkReservationAvailability && checkForPeriodAvailability && checkReservationTimeOverlapping) {

                reservationService.reserveWorkout(shoppingCartItem);

                model.addAttribute("workoutReservationSuccess", true);

                shoppingCartService.deleteById(id);

                fillShoppingCart(model, shoppingCartItem);


            } else {

                fillShoppingCart(model, shoppingCartItem);

                if(!checkReservationAvailability) {

                    periodService.setCapacityFullByPeriodId(shoppingCartItem.getPeriodId());

                    model.addAttribute("checkReservationAvailabilityFailed", true);

                    return "shoppingCart";
                }

                if(!checkForPeriodAvailability) {

                    model.addAttribute("checkForPeriodAvailabilityFailed", true);

                    shoppingCartService.deleteById(id);

                    return "shoppingCart";
                }

                if(!checkReservationTimeOverlapping) {

                    model.addAttribute("checkReservationTimeOverlappingFailed", true);

                    return "shoppingCart";
                }

            }

            return "shoppingCart";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

    private void fillShoppingCart(Model model, ShoppingCart shoppingCartItem) {

        navBarController.setNavBarCustomer("Workouts in shopping cart", "/shoppingCart",
                false, model);

        model.addAttribute("shoppingCartItems", shoppingCartService.findAllByUserId(
                shoppingCartItem.getUserId()));
    }
}
