package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.*;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.AuditoriumService;
import com.project.gymcenter.service.RegisteredUserService;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class UserController {

    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    WorkoutService workoutService;

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
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

            } else if (registeredUser.getUserRole().equals(UserRole.Customer)) {

                System.out.println("Logged in customer");

                request.getSession().setAttribute("currentUserRole", registeredUser.getUserRole());

                request.getSession().setAttribute("loggedInUserId", registeredUser.getUserId());

                return "redirect:/";

            }

            else {

                request.getSession().setAttribute("currentUserRole", registeredUser.getUserRole());

                return "login";
            }

        } else {

            model.addAttribute("invalidCredentials", true);
            model.addAttribute("showRegisterButton", true);

            return "login";
        }

    }

    @RequestMapping("/accountInfo")
    public String editUserDetails(HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            model.addAttribute("showRegisterButton", true);

            return "login";

        } else {

            String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                    navBarController.getNavBarLanguagePath(lang);

            int currentLoggedInUserId = Integer.parseInt(
                    request.getSession().getAttribute("loggedInUserId").toString());

            /* navBarController.setNavBarAdministrator("My account details", "/accountInfo",
                    false, model);

            FillFormWithUserData(model, currentLoggedInUserId);

            FillFormWithUserDataReadOnly(false, model, currentLoggedInUserId); */

            getUserDetailsPageNavBar(request, model, currentLoggedInUserId, navBarLanguagePath);

            return "userDetails";
        }

    }

    @RequestMapping(value="/saveAccountInfo", method= RequestMethod.POST)
    public String saveAccountInfo(@ModelAttribute(name="addEditAccountForm") AddEditAccountForm addEditAccountForm,
                                  HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            model.addAttribute("showRegisterButton", true);

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

                getUserDetailsPageNavBar(request, model, userId, lang);

                model.addAttribute("saveAccountChangesSuccess", true);

                return "userDetails";

            } catch (Exception e) {

                model.addAttribute("saveAccountChangesFailed", true);

                getUserDetailsPageNavBar(request, model, userId, lang);

                return "userDetails";
            }

        }
    }

    @RequestMapping(value="/changePassword", method= RequestMethod.POST)
    public String changePassword(@ModelAttribute(name="changePasswordForm") ChangePasswordForm changePasswordForm,
                                 HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            model.addAttribute("showRegisterButton", true);

            return "login";

        } else {

            int userId = Integer.parseInt(request.getSession().getAttribute("loggedInUserId").toString());

            String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                    navBarController.getNavBarLanguagePath(lang);

            try {

                registeredUserService.changePassword(changePasswordForm.getUserNewPassword(), userId);

                getUserDetailsPageNavBar(request, model, userId, navBarLanguagePath);

                model.addAttribute("passwordChangeSuccess", true);

                return "userDetails";

            } catch (Exception e) {

                navBarController.setNavBarAdministrator("My account details", "/accountInfo",
                        navBarLanguagePath, false, true, model, workoutService.findAll(), auditoriumService.findAll());

                getUserDetailsPageNavBar(request, model, userId, navBarLanguagePath);

                model.addAttribute("passwordChangeFailed", true);

                return "userDetails";
            }

        }

    }

    @RequestMapping("/manageUsers")
    public String manageUsers(HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang);

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            navBarController.setNavBarAdministrator("Manage users", "/manageUsers",
                    navBarLanguagePath, true, true, model, workoutService.findAll(),
                    auditoriumService.findAll());

            List<RegisteredUser> registeredUsers = registeredUserService.findAll();

            model.addAttribute("registeredUsers", registeredUsers);

            return "manageUsers";
        }

    }

    @RequestMapping(value="/usersSearchResult", method=RequestMethod.POST)
    public String usersSearchResult(@ModelAttribute(name="userSearchForm") UserSearchForm userSearchForm, Model model, HttpServletRequest request, @RequestParam(required = false) String lang) {

        List<RegisteredUser> usersFound = registeredUserService.find(userSearchForm.getUserName(),
                userSearchForm.getUserRole(), userSearchForm.getUserSortBy());

        model.addAttribute("registeredUsers", usersFound);

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

        else {

            navBarController.setNavBarAdministrator("Manage users", "/manageUsers",
                    "", true, false, model, workoutService.findAll(), auditoriumService.findAll());

            return "manageUsers";
        }
    }

    @RequestMapping("/userDetails")
    public String userDetails(@RequestParam int id, Model model, HttpServletRequest request,
                              @RequestParam(required = false) String lang) {

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang) + "&" + "id=" + id;

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

        else {

            if(Integer.parseInt(request.getSession().getAttribute("loggedInUserId").toString()) == id) {

                return "redirect:/accountInfo";

            } else {

                navBarController.setNavBarAdministrator("User details", "/userDetails?id=" + id,
                        navBarLanguagePath, false, true, model, workoutService.findAll(), auditoriumService.findAll());

                FillFormWithUserData(model, id);

                FillFormWithUserDataReadOnly(true, model, id);

                return "userDetails";
            }

        }

    }

    @RequestMapping(value="/saveUserDetails", method = RequestMethod.POST)
    public String saveUserDetails(@RequestParam int id, @ModelAttribute(name="addEditAccountForm")
            AddEditAccountForm addEditAccountForm, HttpServletRequest request, Model model,
                                  @RequestParam(required = false) String lang) {

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            try{

                Boolean isRemoved;

                try {

                    if(addEditAccountForm.getUserAccountStatus().equals("Active"))
                        isRemoved = false;
                    else
                        isRemoved = true;

                } catch (Exception e) {

                    isRemoved = registeredUserService.findById(id).getDeleted();

                }

                if(addEditAccountForm.getUserRole().equals(UserRole.Administrator) && isRemoved) {

                    model.addAttribute("changeUserRoleAccountFailed", true); // ADD ERROR

                    getUserDetails(id, model, lang);

                } else {

                    RegisteredUser registeredUser = new RegisteredUser(addEditAccountForm.getUserRole(), isRemoved);

                    registeredUserService.updateAccountStatus(registeredUser, id);

                    getUserDetails(id, model, lang);

                    model.addAttribute("saveAccountChangesSuccess", true);

                }

                return "userDetails";

            } catch (Exception e) {

                model.addAttribute("saveAccountChangesFailed", true);

                getUserDetails(id, model, lang);

                e.printStackTrace();

                return "userDetails";
            }

        }


    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();

        return "redirect:/";
    }

    private void getUserDetails(int id, Model model, String navBarLanguagePath) {

        navBarController.setNavBarAdministrator("User details", "/userDetails?id=" + id,
                navBarLanguagePath, false, true, model, workoutService.findAll(), auditoriumService.findAll());

        FillFormWithUserData(model, id);

        FillFormWithUserDataReadOnly(true, model, id);

    }

    public String[] splitUserDateBirth(LocalDate userDateBirth) {

        return userDateBirth.toString().split("-");
    }

    private void FillFormWithUserData(Model model, int userId) {

        RegisteredUser userData = registeredUserService.findById(userId);

        model.addAttribute("loggedInUser", userData);

        model.addAttribute("loggedInUserYearOfBirth",
                splitUserDateBirth(userData.getUserDateBirth())[0]);

        model.addAttribute("loggedInUserMonthOfBirth",
                splitUserDateBirth(userData.getUserDateBirth())[1]);

        model.addAttribute("loggedInUserDayOfBirth",
                splitUserDateBirth(userData.getUserDateBirth())[2]);

        model.addAttribute("loggedInUserIsRemoved", userData.getDeleted());

        setUserAccountStatusReadOnly(model, userData);

        System.out.println(userData);
    }

    private void FillFormWithUserDataReadOnly(boolean readOnly, Model model, int id) {

        if(readOnly) {

            model.addAttribute("formAction", "/saveUserDetails?id=" + id);

            model.addAttribute("inputReadOnly", true);

            model.addAttribute("userPasswordButtonVisible", false);
            model.addAttribute("cancelAddEditAccountVisible", false);
            model.addAttribute("cancelUserDetailsAccountVisible", true);
            model.addAttribute("saveChangesButtonVisible", false);

            model.addAttribute("userRoleVisible", true);
            model.addAttribute("accountStatusVisible", true);

        } else {

            model.addAttribute("formAction", "/saveAccountInfo");

            model.addAttribute("inputReadOnly", false);

            model.addAttribute("userPasswordVisible", true);
            model.addAttribute("cancelAddEditAccountVisible", true);
            model.addAttribute("cancelUserDetailsAccountVisible", false);
            model.addAttribute("saveChangesButtonVisible", true);

            model.addAttribute("userRoleVisible", false);
            model.addAttribute("accountStatusVisible", false);

        }

    }

    private void setUserAccountStatusReadOnly(Model model, RegisteredUser userData) {

        if(userData.getUserRole().equals(UserRole.Administrator))

            model.addAttribute("userAccountStatusReadOnly", true);

        else
            model.addAttribute("userAccountStatusReadOnly", false);
    }

    private void getUserDetailsPageNavBar(HttpServletRequest request, Model model, int userId, String navBarLanguagePath) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            navBarController.setNavBarCustomer("My account details",
                    "/accountInfo", navBarLanguagePath, false, true, model);

        } else {

            navBarController.setNavBarAdministrator("My account details",
                    "/accountInfo", navBarLanguagePath, false, true, model, workoutService.findAll(), auditoriumService.findAll());

        }

        FillFormWithUserData(model, userId);

        FillFormWithUserDataReadOnly(false, model, userId);
    }
}
