package com.sergeev.visitcard.web.controller;

import com.sergeev.visitcard.data.Country;
import com.sergeev.visitcard.data.Town;
import com.sergeev.visitcard.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebController {

    MappingService mappingService;

    @Autowired
    public WebController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    /*Начало нормальной главной страницы*/
    @GetMapping("/")
    public ModelAndView getMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    /*Получение страницы page_2*/
    @GetMapping("/save")
    public String getPage(Model model) {
        List<Town> towns = mappingService.getTowns();
        List<Country> countries = mappingService.getCountries();
        model.addAttribute("countTown", towns.size());
        model.addAttribute("towns", towns);
        model.addAttribute("countries", countries);
        model.addAttribute("countCountries", countries.size());
        return "page_2";
    }
}
