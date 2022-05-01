package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.ChangePasswordForm;
import com.project.gymcenter.dao.form.LoginForm;
import com.project.gymcenter.dao.form.AddEditAccountForm;
import com.project.gymcenter.dao.form.WorkoutSearchForm;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class UserController {

    @Autowired
    RegisteredUserService registeredUserService;

    NavBarController navBarController = new NavBarController();

    @RequestMapping("/login")
    public String login(Model model) {

        model.addAttribute("showRegisterButton", true);
        model.addAttribute("showCancelButton", true);

        return "login";
    }

    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    @RequestMapping(value="/sendRegistrationData", method = RequestMethod.POST)
    public String sendRegistrationData(@ModelAttribute(name="registerForm") AddEditAccountForm addEditAccountForm,
                                       Model model) {

        LocalDate userDateBirth = LocalDate.of(addEditAccountForm.getYearOfUserDateBirth(),
                addEditAccountForm.getMonthOfUserDateBirth(), addEditAccountForm.getDayOfUserDateBirth());

        LocalDateTime userDateTimeRegistration = LocalDateTime.now();

        RegisteredUser registeredUser = new RegisteredUser(addEditAccountForm.getUserName(),
                addEditAccountForm.getUserPassword(), addEditAccountForm.getUserEmail(),
                addEditAccountForm.getUserFirstName(), addEditAccountForm.getUserLastName(),
                userDateBirth, addEditAccountForm.getUserAddress(), addEditAccountForm.getUserPhoneNumber(),
                userDateTimeRegistration);

        try {

            registeredUserService.add(registeredUser);

        } catch (Exception e) {

            model.addAttribute("registrationFailed", true);

            return "register";
        }

        model.addAttribute("registrationSuccessful", true);
        model.addAttribute("continueAsGuestButton", true);

        return "login";

    }

    @RequestMapping(value="/", method= RequestMethod.POST)
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

                request.getSession().setAttribute("loggedInUserId", registeredUser.getUserId());

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

    @RequestMapping("/accountInfo")
    public String editUserDetails(HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            return "login";

        } else {

            int currentLoggedInUserId = Integer.parseInt(
                    request.getSession().getAttribute("loggedInUserId").toString());

            FillFormWithLoggedInUserData(model, currentLoggedInUserId);

            return "editUserDetails";
        }

    }

    @RequestMapping(value="/saveAccountInfo", method= RequestMethod.POST)
    public String saveAccountInfo(@ModelAttribute(name="addEditAccountForm") AddEditAccountForm addEditAccountForm,
                                  HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            return "login";

        } else {

            int userId = Integer.parseInt(request.getSession().getAttribute("loggedInUserId").toString());

            try {

                LocalDate userDateBirth = LocalDate.of(addEditAccountForm.getYearOfUserDateBirth(),
                        addEditAccountForm.getMonthOfUserDateBirth(), addEditAccountForm.getDayOfUserDateBirth());

                RegisteredUser registeredUser = new RegisteredUser(addEditAccountForm.getUserName(),
                        addEditAccountForm.getUserEmail(), addEditAccountForm.getUserFirstName(),
                        addEditAccountForm.getUserLastName(), userDateBirth, addEditAccountForm.getUserAddress(),
                        addEditAccountForm.getUserPhoneNumber());

                registeredUserService.update(registeredUser, userId);

                FillFormWithLoggedInUserData(model, userId);

                model.addAttribute("saveAccountChangesSuccess", true);

                return "editUserDetails";

            } catch (Exception e) {

                model.addAttribute("saveAccountChangesFailed", true);

                FillFormWithLoggedInUserData(model, userId);

                e.printStackTrace();

                return "editUserDetails";
            }

        }
    }

    @RequestMapping(value="/changePassword", method= RequestMethod.POST)
    public String changePassword(@ModelAttribute(name="changePasswordForm") ChangePasswordForm changePasswordForm,
                                 HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            return "login";

        } else {

            int userId = Integer.parseInt(request.getSession().getAttribute("loggedInUserId").toString());

            try {

                registeredUserService.changePassword(changePasswordForm.getUserNewPassword(), userId);

                FillFormWithLoggedInUserData(model, userId);

                model.addAttribute("passwordChangeSuccess", true);

                return "editUserDetails";

            } catch (Exception e) {

                FillFormWithLoggedInUserData(model, userId);

                model.addAttribute("passwordChangeFailed", true);

                return "editUserDetails";
            }

        }

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();

        return "redirect:/";
    }

    public String[] splitUserDateBirth(LocalDate userDateBirth) {

        return userDateBirth.toString().split("-");
    }

    private void FillFormWithLoggedInUserData(Model model, int currentLoggedInUserId) {

        navBarController.setNavBarAdministrator("My account details", false, model);

        RegisteredUser currentLoggedInUserData = registeredUserService.findById(currentLoggedInUserId);

        model.addAttribute("loggedInUser", currentLoggedInUserData);

        model.addAttribute("loggedInUserYearOfBirth",
                splitUserDateBirth(currentLoggedInUserData.getUserDateBirth())[0]);

        model.addAttribute("loggedInUserMonthOfBirth",
                splitUserDateBirth(currentLoggedInUserData.getUserDateBirth())[1]);

        model.addAttribute("loggedInUserDayOfBirth",
                splitUserDateBirth(currentLoggedInUserData.getUserDateBirth())[2]);
    }
}
