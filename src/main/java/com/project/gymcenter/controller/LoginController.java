package com.project.gymcenter.controller;

import com.project.gymcenter.form.LoginForm;
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

        if(registeredUserService.findOne(userName, password) != null) {


            return "redirect:/workouts";
            //return "workouts";

        } else {

            model.addAttribute("invalidCredentials", true);

            return "login";
        }
    }
}
