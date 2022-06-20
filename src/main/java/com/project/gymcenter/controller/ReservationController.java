package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.ReservationSearchForm;
import com.project.gymcenter.model.Period;
import com.project.gymcenter.model.PeriodReserved;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.PeriodService;
import com.project.gymcenter.service.RegisteredUserService;
import com.project.gymcenter.service.ReservationService;
import com.project.gymcenter.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    PeriodService periodService;

    @Autowired
    RegisteredUserService registeredUserService;

    NavBarController navBarController = new NavBarController();

    @RequestMapping(value = "/reserveWorkout")
    public String reserveWorkout(@RequestParam Long id, HttpServletRequest request, Model model) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            ShoppingCart shoppingCartItem = shoppingCartService.findPeriodByShoppingCartId(id);

            boolean checkReservationAvailability = reservationService.checkReservationAvailability(id);

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

    @RequestMapping(value = "/reservations")
    public String getReservations(HttpServletRequest request, Model model) {

        //TODO on this page, you can add search and filter methods and hiding already started workout periods...

        Long userId;

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            navBarController.setNavBarCustomer("Reserved workout periods", "/reservations",
                    true, model);

            model.addAttribute("isAdministrator", false);

            model.addAttribute("workoutReservations", reservationService.findAllByUserId(userId));

            model.addAttribute("isCancelReservationExpired", LocalDateTime.now().plusDays(1));

            return "reservation";

        } else if (Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            navBarController.setNavBarAdministrator("Reserved all workout periods", "/reservations",
                    true, model);

            model.addAttribute("isAdministrator", true);

            model.addAttribute("workoutReservations", reservationService.findAll());

            model.addAttribute("registeredUsers", registeredUserService.findAll());

            return "reservation";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

    @RequestMapping(value = "/reservationsSearchResult", method = RequestMethod.POST)
    public String reservationsSearchResult(@ModelAttribute(name="reservationSearchForm") ReservationSearchForm
                                                       reservationSearchForm, Model model, HttpServletRequest request) {
        List<PeriodReserved> reservationList;

        model.addAttribute("registeredUsers", registeredUserService.findAll());

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            reservationList = reservationService.find(userId.toString(), reservationSearchForm.getReservationSortBy());

            model.addAttribute("workoutReservations", reservationList);

            navBarController.setNavBarCustomer("Reserved all workout periods", "/reservations",
                    true, model);

        } else if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            reservationList = reservationService.find(reservationSearchForm.getCustomerFilter(),
                    reservationSearchForm.getReservationSortBy());

            model.addAttribute("workoutReservations", reservationList);

            model.addAttribute("isAdministrator", true);

            navBarController.setNavBarAdministrator("Reserved all workout periods", "/reservations",
                    true, model);

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }


        return "reservation";
    }

    @RequestMapping(value = "/cancelReservation")
    public String cancelReservation(@RequestParam Long id, HttpServletRequest request, Model model) {

        if (Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            reservationService.deleteById(id);

            return "redirect:/reservations";

        } else if (Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            return "login";

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
