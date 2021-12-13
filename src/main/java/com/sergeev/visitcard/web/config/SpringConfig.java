package com.sergeev.visitcard.web.config;


import com.sergeev.visitcard.service.captcha.CaptchaGen;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.image.BufferedImage;

@Configuration
public class SpringConfig {


    @Bean
    public CaptchaGen captchaGen() {
        return new CaptchaGen();
    }
}
