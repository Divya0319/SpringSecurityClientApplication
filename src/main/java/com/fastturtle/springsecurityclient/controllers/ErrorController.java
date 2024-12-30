package com.fastturtle.springsecurityclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "custom-access-denied";
    }
}
