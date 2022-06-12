package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.AddEditAuditoriumForm;
import com.project.gymcenter.dao.form.AuditoriumSearchForm;
import com.project.gymcenter.model.Auditorium;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AuditoriumController {

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    NavBarController navBarController = new NavBarController();

    @RequestMapping("/auditoriums")
    public String auditoriums(HttpServletRequest request, Model model) {

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                    true, model);

            FillFormWithAuditoriumsData(model);

            return "auditoriums";
        }
    }

    @RequestMapping(value="/auditoriumsSearchResult", method=RequestMethod.POST)
    public String auditoriumsSearchResult (@ModelAttribute(name="auditoriumSearchForm")AuditoriumSearchForm
                                                       auditoriumSearchForm, Model model, HttpServletRequest request) {

        List<Auditorium> auditoriumsFound = auditoriumService.find(auditoriumSearchForm.getAuditoriumId(),
                auditoriumSearchForm.getAuditoriumSortBy());

        model.addAttribute("auditoriumList", auditoriumsFound);

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                    true, model);

            return "auditoriums";

        }

    }

    @RequestMapping("saveAuditoriumDetails")
    public String auditoriumDetails(@ModelAttribute(name="addEditAuditoriumForm") AddEditAuditoriumForm
                                            addEditAuditoriumForm, Model model, HttpServletRequest request) {

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                    false, model);

            Auditorium auditorium = new Auditorium(addEditAuditoriumForm.getAuditoriumId(),
                    addEditAuditoriumForm.getAuditoriumCapacity());

            auditoriumService.update(auditorium);

            return "redirect:/auditoriums";

        }

    }

    @RequestMapping("/removeAuditorium")
    public String removeAuditorium (@RequestParam String id, Model model, HttpServletRequest request) {

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            if(auditoriumService.remove(id) == 0) {

                navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                        false, model);

                model.addAttribute("auditoriumRemoveFailed", true);

                FillFormWithAuditoriumsData(model);

                return "auditoriums";

            } else {

                navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                        false, model);

                model.addAttribute("auditoriumRemoveSuccess", true);

                FillFormWithAuditoriumsData(model);

                return "auditoriums";

            }
        }
    }

    @RequestMapping(value="/addNewAuditorium", method = RequestMethod.POST)
    public String addNewAuditorium(@ModelAttribute(name="addEditAuditoriumForm") AddEditAuditoriumForm
                                               addEditAuditoriumForm, Model model, HttpServletRequest request) {

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            Auditorium auditorium = new Auditorium(addEditAuditoriumForm.getAuditoriumId(),
                    addEditAuditoriumForm.getAuditoriumCapacity());

            if(auditoriumService.add(auditorium)) {

                return "redirect:/auditoriums";


            } else {

                navBarController.setNavBarAdministrator("Auditoriums", "/auditoriums",
                        true, model);

                FillFormWithAuditoriumsData(model);

                model.addAttribute("auditoriumAddFailed", true);

                return "auditoriums";

            }


        }


    }

    private void FillFormWithAuditoriumsData(Model model) {

        List<Auditorium> auditoriumList = auditoriumService.findAll();

        List<Auditorium> addMode = new ArrayList<>();

        addMode.add(new Auditorium("", 1L));

        System.out.println(auditoriumList);

        model.addAttribute("auditoriumList", auditoriumList);

        model.addAttribute("auditoriumAddId", addMode);
    }

}
