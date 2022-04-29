package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.LoginForm;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    RegisteredUserService registeredUserService;

    @RequestMapping("/login")
    public String login(Model model) {

        model.addAttribute("showRegisterButton", true);
        model.addAttribute("showCancelButton", true);

        return "login";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model,
                        HttpServletRequest request) {

        RegisteredUser registeredUser = registeredUserService.findOne(loginForm.getUserName(), loginForm.getPassword());

        if(registeredUser != null) {

            if(registeredUser.getUserRole().equals(UserRole.Administrator)) {

                /*

                //request.getSession().getAttribute("currentUserRole")

                //request.getSession().setAttribute("currentUserRole", registeredUser.getUserRole());

                //request.getSession().invalidate();

               */

                request.getSession().setAttribute("currentUserRole", registeredUser.getUserRole());

                return "redirect:/workouts";
            }

            else {

                request.getSession().setAttribute("currentUserRole", registeredUser.getUserRole());

                return "login"; // NOT IMPLEMENTED YET FOR CUSTOMER
            }

        } else {

            model.addAttribute("invalidCredentials", true);

            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();

        return "redirect:/";
    }
}
