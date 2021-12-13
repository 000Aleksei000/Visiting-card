package com.sergeev.visitcard.web.controller;

import com.sergeev.visitcard.service.captcha.CaptchaGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/captcha")
public class CaptchaServController {
    CaptchaGen captchaGen;

    @Autowired
    public CaptchaServController(CaptchaGen captchaGen) {
        this.captchaGen = captchaGen;
    }

    @GetMapping(value = "/getCaptcha" , produces = "application/json")
    public String getCaptcha(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("captcha"));
        return "sfdadfadfs";
    }

    @GetMapping("/getImg")
    public void getImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaGen.createImg(request, response);
    }
}
