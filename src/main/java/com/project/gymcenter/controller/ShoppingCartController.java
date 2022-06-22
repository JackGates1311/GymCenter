package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.AddWorkoutToShoppingCartForm;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    NavBarController navBarController = new NavBarController();

    MessageController messageController = new MessageController();

    @RequestMapping(value="/addWorkoutToShoppingCart", method = RequestMethod.POST)
    public String addWorkoutToShoppingCart(@ModelAttribute(name="addWorkoutToShoppingCartForm") AddWorkoutToShoppingCartForm
                                                   addWorkoutToShoppingCartForm, Model model, HttpServletRequest request) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(), String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            ShoppingCart shoppingCart = new ShoppingCart(addWorkoutToShoppingCartForm.getWorkoutPeriodId(), userId);

            if(shoppingCartService.checkIfAlreadyAddedToShoppingCart(shoppingCart)) {

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message", "Workout has been already added to your " +
                                "shopping cart", true);

            } else {

                shoppingCartService.addWorkoutToShoppingCart(shoppingCart);

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message", "Workout has been added to shopping cart successfully",
                        false);
            }

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

        return "message";

    }

    @RequestMapping("/shoppingCart")
    public String getShoppingCartItems(HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang);

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            navBarController.setNavBarCustomer("Workouts in shopping cart", "/shoppingCart", navBarLanguagePath,
                    false, true, model);

            model.addAttribute("shoppingCartItems", shoppingCartService.findAllByUserId(userId));

            return "shoppingCart";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

    @RequestMapping(value = "/removeShoppingCartItem")
    public String removeShoppingCartItem(@RequestParam Long id, HttpServletRequest request, Model model) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            shoppingCartService.deleteById(id);

            return "redirect:/shoppingCart";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

}
