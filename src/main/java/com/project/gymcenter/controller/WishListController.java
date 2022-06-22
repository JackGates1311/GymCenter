package com.project.gymcenter.controller;

import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class WishListController {

    NavBarController navBarController = new NavBarController();

    MessageController messageController = new MessageController();

    @Autowired
    WishListService wishListService;

    @RequestMapping(value = "/wishlist")
    private String wishList(HttpServletRequest request, Model model,
                            @RequestParam(required = false) String lang) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                    navBarController.getNavBarLanguagePath(lang);

            navBarController.setNavBarCustomer("Workouts wishlist", "/wishlist", navBarLanguagePath,
                    false, true, model);

            model.addAttribute("wishListItems", wishListService.getWishListByUserId(userId));

            return "wishlist";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

    @RequestMapping(value = "/removeWishListItem")
    public String removeWishListItem(@RequestParam Long id, HttpServletRequest request, Model model) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            wishListService.deleteById(id);

            return "redirect:/wishlist";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

    @RequestMapping(value = "/addWorkoutToWishlist")
    public String addWorkoutToWishList(@RequestParam Long id, HttpServletRequest request, Model model) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            if(wishListService.checkIfAlreadyExistsInWishList(id, userId)) {

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message", "Workout has been already added to your " +
                                "wishlist", true);

            } else {

                wishListService.addWorkoutToWishList(id, userId);

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message", "Workout has been added to wishlist successfully",
                        false);
            }

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

        return "message";
    }

}
