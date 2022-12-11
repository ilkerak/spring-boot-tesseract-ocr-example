package com.example.ocrdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {


    @GetMapping(path = {"/", "/index", "/upload"})
    public ModelAndView dashboard() {
        return new ModelAndView("upload");
    }

}
