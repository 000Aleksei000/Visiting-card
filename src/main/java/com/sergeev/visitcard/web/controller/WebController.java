package com.sergeev.visitcard.web.controller;

import com.sergeev.visitcard.data.crud.Country;
import com.sergeev.visitcard.data.crud.Town;
import com.sergeev.visitcard.service.basketService.BasketService;
import com.sergeev.visitcard.service.captcha.CaptchaGen;
import com.sergeev.visitcard.service.crudService.MappingService;
import com.sergeev.visitcard.service.loggerService.LoggerServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class WebController {

    MappingService mappingService;
    BasketService basketService;
    LoggerServ loggerService;
    CaptchaGen captchaGen;

    @Autowired
    public WebController(MappingService mappingService, BasketService basketService, LoggerServ loggerService, CaptchaGen captchaGen) {
        this.mappingService = mappingService;
        this.basketService = basketService;
        this.loggerService = loggerService;
        this.captchaGen = captchaGen;
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

    /*-----------------------------------------Logger---------------------------------------------*/
    @GetMapping("/logger")
    public ModelAndView getLogger(HttpServletRequest request, HttpServletResponse response) {
        String cookieStr = "";
        Cookie[] requestCookies = request.getCookies();
        if(requestCookies != null) {
            for (Cookie cookie : requestCookies) {
                if (cookie.getName().equals("cookieForLogger")) {
                    cookieStr = cookie.getValue();
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logger");
        modelAndView.addObject("Logs", loggerService.getLogs(cookieStr));
        return modelAndView;
    }

    @PostMapping("/logger")
    public void addLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String log = (String) request.getParameter("Log");
        addCookieAndLog(request, response, log);
        response.sendRedirect("/logger");
    }

    /*--------------------------------------------------------------------------------------------*/

    /*----------------------------------------CAPTCHA----------------------------------------------------*/
    @GetMapping("/captcha")
    public ModelAndView captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("captcha");
//        addCookieForCaptcha(request, response);
        return modelAndView;
    }

    /*--------------------------------------------------------------------------------------------*/

    private void addCookie(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] requestCookies = request.getCookies();
        boolean flag = false;
        String cookieStr = "";
        if (requestCookies != null) {
            for (Cookie cookie : requestCookies) {
                if (cookie.getName().equals("cookieForBasket")) {
                    flag = true;
                }
            }
        }
        if (flag) {

        } else {
            Cookie cookieAdd = new Cookie("cookieForBasket", UUID.randomUUID().toString());
            response.addCookie(cookieAdd);
            basketService.addCostumer(cookieAdd.getValue());
        }

    }

    private void addCookieAndLog(HttpServletRequest request, HttpServletResponse response, String log) {
        boolean flag = false;
        String cookieStr = "";
        Cookie[] requestCookies = request.getCookies();
        if(requestCookies != null) {
            for (Cookie cookie : requestCookies) {
                if (cookie.getName().equals("cookieForLogger")) {
                    flag = true;
                    cookieStr = cookie.getValue();
                }
            }
        }
        if (flag) {
            loggerService.saveLog(cookieStr, log);
        } else {
            Cookie cookie = new Cookie("cookieForLogger" , UUID.randomUUID().toString());
            response.addCookie(cookie);
            loggerService.saveLog(cookie.getValue(), log);
        }
    }

//    public void addCookieForCaptcha(HttpServletRequest request, HttpServletResponse response) {
//        boolean flag = true;
//        Cookie[] requestCookies = request.getCookies();
//        if (requestCookies != null) {
//            for (Cookie cookie : requestCookies) {
//                if (cookie.getName().equals("cookieForCaptcha")) {
//                    flag = false;
//                }
//            }
//        }
//        if (flag) {
//            Cookie cookie = new Cookie("cookieForCaptcha", UUID.randomUUID().toString());
//            response.addCookie(cookie);
//        }
//    }


}
