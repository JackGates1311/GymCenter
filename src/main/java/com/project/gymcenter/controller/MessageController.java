package com.project.gymcenter.controller;

import org.springframework.ui.Model;

public class MessageController {

    protected void newMessageTemplate(Model model, String textTitle, String textHeader, String textMessage,
                                      Boolean isError) {

        model.addAttribute("textTitle", textTitle);
        model.addAttribute("textHeader", textHeader);
        model.addAttribute("textMessage", textMessage);
        model.addAttribute("textMessageError", textMessage);
        model.addAttribute("isError", isError);

    }

}
