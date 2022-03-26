package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.RegisterForm;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class RegisterController {

    @Autowired
    RegisteredUserService registeredUserService;

    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    @RequestMapping(value="/sendRegistrationData", method = RequestMethod.POST)
    public String sendRegistrationData(@ModelAttribute(name="registerForm") RegisterForm registerForm, Model model) {

        LocalDate userDateBirth = LocalDate.of(registerForm.getYearOfUserDateBirth(),
                registerForm.getMonthOfUserDateBirth(), registerForm.getDayOfUserDateBirth());

        LocalDateTime userDateTimeRegistration = LocalDateTime.now();

        RegisteredUser registeredUser = new RegisteredUser(registerForm.getUserName(), registerForm.getUserPassword(),
                registerForm.getUserEmail(), registerForm.getUserFirstName(), registerForm.getUserLastName(),
                userDateBirth, registerForm.getUserAddress(), registerForm.getUserPhoneNumber(),
                userDateTimeRegistration);

        try {

            registeredUserService.add(registeredUser);

        } catch (Exception e) {

            model.addAttribute("registrationFailed", true);

            return "register";
        }

        model.addAttribute("registrationSuccessful", true);

        return "login";
    }
}
