package com.project.gymcenter.controller;

import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.AuditoriumService;
import com.project.gymcenter.service.LoyaltyCardService;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class LoyaltyCardController {

    @Autowired
    LoyaltyCardService loyaltyCardService;

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    WorkoutService workoutService;

    NavBarController navBarController = new NavBarController();

    MessageController messageController = new MessageController();

    @RequestMapping("/requestLoyaltyCard")
    public String requestLoyaltyCard(HttpServletRequest request, Model model) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(), String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            if(loyaltyCardService.findIfUserIsAlreadyHaveLoyaltyCard(userId)) {

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message",
                        "Your already have loyalty card or you need to wait for approve",true);

            } else {

                loyaltyCardService.sendRequestForLoyaltyCard(userId);

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message",
                        "Your request for loyalty card has been successfully processed",false);
            }


        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

        return "message";
    }

    @RequestMapping("/approveLoyaltyCard")
    public String approveLoyaltyCard(@RequestParam Long id, Model model) {

        loyaltyCardService.approveLoyaltyCard(id);

        return "redirect:/loyaltyCardRequests";
    }

    @RequestMapping("/declineLoyaltyCard")
    public String declineLoyaltyCard(@RequestParam Long id, Model model) {

        loyaltyCardService.declineLoyaltyCard(id);

        return "redirect:/loyaltyCardRequests";
    }

    @RequestMapping("/loyaltyCardRequests")
    public String loyaltyCardRequests(HttpServletRequest request, Model model,
                                      @RequestParam(required = false) String lang){

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                    navBarController.getNavBarLanguagePath(lang);

            navBarController.setNavBarAdministrator("Loyalty card requests", "/loyaltyCardRequests",
                    navBarLanguagePath, false, true, model, workoutService.findAll(), auditoriumService.findAll());

            model.addAttribute("loyaltyCardList", loyaltyCardService.findLoyaltyCardRequests());


        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

        return "loyaltyCard";
    }

}
