package com.project.gymcenter.controller;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class InternationalizationController {

    public String getStringValue(String lang, String key) {

        if(Objects.isNull(lang))
            lang = "";

        return ResourceBundle.getBundle("messages/messages", new Locale(lang)).getString(key);
    }

}
