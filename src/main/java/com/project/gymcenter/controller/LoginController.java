package com.project.gymcenter.controller;

import com.project.gymcenter.form.LoginForm;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    RegisteredUserService registeredUserService;

    @RequestMapping("/")
    public String login() {

        return "login";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model) {

        String userName = loginForm.getUserName();
        String password = loginForm.getPassword();

        RegisteredUser registeredUser = registeredUserService.findOne(userName, password);

        if(registeredUser != null) {

            if(registeredUser.getUserRole().equals(UserRole.Administrator))
                return "redirect:/workouts";
            else
                return "login"; // NOT IMPLEMENTED YET FOR CUSTOMER

        } else {

            model.addAttribute("invalidCredentials", true);

            return "login";
        }
    }
}
