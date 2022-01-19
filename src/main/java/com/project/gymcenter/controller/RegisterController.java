package com.project.gymcenter.controller;

import com.project.gymcenter.form.RegisterForm;
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

        String userName = registerForm.getUserName();
        String userPassword = registerForm.getUserPassword();
        String userEmail = registerForm.getUserEmail();
        String userFirstName = registerForm.getUserFirstName();
        String userLastName = registerForm.getUserLastName();
        int dayOfUserDateBirth = registerForm.getDayOfUserDateBirth();
        int monthOfUserDateBirth = registerForm.getMonthOfUserDateBirth();
        int yearOfUserDateBirth = registerForm.getYearOfUserDateBirth();
        String userAddress = registerForm.getUserAddress();
        String userPhoneNumber = registerForm.getUserPhoneNumber();
        LocalDate userDateBirth = LocalDate.of(yearOfUserDateBirth, monthOfUserDateBirth, dayOfUserDateBirth);
        LocalDateTime userDateTimeRegistration = LocalDateTime.now();

        RegisteredUser registeredUser = new RegisteredUser(userName, userPassword, userEmail, userFirstName,
                userLastName, userDateBirth, userAddress, userPhoneNumber, userDateTimeRegistration);

        try {

            registeredUserService.add(registeredUser);

        } catch (Exception e) {

            model.addAttribute("registrationFailed", true);

            return "register";
        }

        System.out.println("Success");

        model.addAttribute("registrationSuccessfull", true);

        return "login";

    }
}
