package com.project.gymcenter.controller;

import com.project.gymcenter.model.Auditorium;
import com.project.gymcenter.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AuditoriumController {

    @Autowired
    AuditoriumService auditoriumService;

    NavBarController navBarController = new NavBarController();

    @RequestMapping("/auditoriums")
    public String auditoriums(HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            return "login";

        } else {

            navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                    true, model);

            List<Auditorium> auditoriumList = auditoriumService.findAll();

            System.out.println(auditoriumList);

            model.addAttribute("auditoriumList", auditoriumList);

            return "auditoriums";
        }
    }

}
