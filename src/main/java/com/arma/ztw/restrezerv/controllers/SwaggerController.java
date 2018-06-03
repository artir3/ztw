package com.arma.ztw.restrezerv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SwaggerController {

    @GetMapping
    public String swaggerui(){
        return "redirect:/swagger-ui.html";
    }
}