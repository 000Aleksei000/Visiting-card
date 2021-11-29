package com.sergeev.visitcard.web.controller;

import com.sergeev.visitcard.data.Country;
import com.sergeev.visitcard.data.People;
import com.sergeev.visitcard.data.Town;
import com.sergeev.visitcard.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    MappingService mappingService;

    @Autowired
    public WebController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @GetMapping("/save")
    public String getPage() {
        return "ok";
    }

    @PostMapping("/save")
    public String savePeople(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String townPar = request.getParameter("town");
        String countryPar = request.getParameter("country");
        System.out.println("Пошла возня!");
        boolean result = mappingService.savePeopleFromWeb(name, lastName, age, townPar, countryPar);
        if(result) {
            return "ok";
        } else
            return "badPage";
    }
}
