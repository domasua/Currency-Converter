package com.IBMtask.currencyConverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WebController {

    // call HTML
    @GetMapping("/home")
    public String welcome(){
        return "index";
    }
}
