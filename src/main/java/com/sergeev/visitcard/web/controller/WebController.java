package com.sergeev.visitcard.web.controller;

import com.sergeev.visitcard.data.crud.Country;
import com.sergeev.visitcard.data.crud.Town;
import com.sergeev.visitcard.service.basketService.BasketService;
import com.sergeev.visitcard.service.crudService.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class WebController {

    MappingService mappingService;
    BasketService basketService;

    @Autowired
    public WebController(MappingService mappingService, BasketService basketService) {
        this.mappingService = mappingService;
        this.basketService = basketService;
    }

    /*Начало нормальной главной страницы*/
    @GetMapping("/")
    public ModelAndView getMain(HttpServletRequest request , HttpServletResponse response) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    /*Получение страницы page_2*/
    @GetMapping("/save")
    public String getPage(Model model, HttpServletRequest request) {
        List<Town> towns = mappingService.getTowns();
        List<Country> countries = mappingService.getCountries();
        model.addAttribute("countTown", towns.size());
        model.addAttribute("towns", towns);
        model.addAttribute("countries", countries);
        model.addAttribute("countCountries", countries.size());
        return "page_2";
    }
    /*Получение страницы магазина*/
    @GetMapping("/shop")
    public ModelAndView getShop(HttpServletRequest request, HttpServletResponse response) {
        addCookie(request, response);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop");
        return modelAndView;
    }

    /*Получение страницы корзины*/
    @GetMapping("/basket")
    public ModelAndView getBucket(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basket");
        return modelAndView;
    }



    private void addCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] requestCookies = request.getCookies();
        if (requestCookies != null) {
            for (Cookie cookie : requestCookies) {
                if (!(cookie.getName().equals("cookieForBasket"))) {
                    Cookie cookieAdd = new Cookie("cookieForBasket", UUID.randomUUID().toString());
                    response.addCookie(cookieAdd);
                    basketService.addCostumer(cookieAdd.getValue());
                }
            }
        } else {
            Cookie cookieAdd = new Cookie("cookieForBasket", UUID.randomUUID().toString());
            response.addCookie(cookieAdd);
            basketService.addCostumer(cookieAdd.getValue());
        }
    }

}
