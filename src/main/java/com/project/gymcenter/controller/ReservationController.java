package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.ReservationSearchForm;
import com.project.gymcenter.model.Period;
import com.project.gymcenter.model.PeriodReserved;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.*;
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
    AuditoriumService auditoriumService;

    @Autowired
    WorkoutService workoutService;

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
    public String reserveWorkout(@RequestParam Long id, HttpServletRequest request, Model model,
                                 @RequestParam(required = false) String lang) {

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang);

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

                fillShoppingCart(model, shoppingCartItem, navBarLanguagePath);


            } else {

                fillShoppingCart(model, shoppingCartItem, navBarLanguagePath);

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
    public String getReservations(HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        //TODO on this page, you can add search and filter methods and hiding already started workout periods...

        Long userId;

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang);

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            navBarController.setNavBarCustomer("Reserved workout periods", "/reservations",
                    navBarLanguagePath, true, true, model);

            model.addAttribute("isAdministrator", false);

            model.addAttribute("workoutReservations", reservationService.findAllByUserId(userId));

            model.addAttribute("isCancelReservationExpired", LocalDateTime.now().plusDays(1));

            return "reservation";

        } else if (Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            navBarController.setNavBarAdministrator("Reserved all workout periods", "/reservations",
                    navBarLanguagePath, true, true, model, workoutService.findAll(),
                    auditoriumService.findAll());

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
    public String reservationsSearchResult(@ModelAttribute(name="reservationSearchForm") ReservationSearchForm reservationSearchForm, Model model, HttpServletRequest request, @RequestParam(required = false) String lang) {

        List<PeriodReserved> reservationList;

        model.addAttribute("registeredUsers", registeredUserService.findAll());

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            reservationList = reservationService.find(userId.toString(), reservationSearchForm.getReservationSortBy());

            model.addAttribute("workoutReservations", reservationList);

            navBarController.setNavBarCustomer("Reserved all workout periods", "/reservations",
                    "", true, false, model);

        } else if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            reservationList = reservationService.find(reservationSearchForm.getCustomerFilter(),
                    reservationSearchForm.getReservationSortBy());

            model.addAttribute("workoutReservations", reservationList);

            model.addAttribute("isAdministrator", true);

            navBarController.setNavBarAdministrator("Reserved all workout periods", "/reservations",
                    "", true, false, model, workoutService.findAll(),
                    auditoriumService.findAll());

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

    private void fillShoppingCart(Model model, ShoppingCart shoppingCartItem, String path) {

        navBarController.setNavBarCustomer("Workouts in shopping cart", "/shoppingCart", path,
                false, true, model);

        model.addAttribute("shoppingCartItems", shoppingCartService.findAllByUserId(
                shoppingCartItem.getUserId()));
    }
}
