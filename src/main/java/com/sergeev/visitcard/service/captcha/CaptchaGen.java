package com.sergeev.visitcard.service.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


public class CaptchaGen {

    private BufferedImage bufferedImage;



    public void createImg(HttpServletRequest request, HttpServletResponse response) {
        bufferedImage = new BufferedImage(160, 35, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(new Color(169,169,169));
        g2.fillRect(0,0,160,35);
        g2.setColor(new Color(255,255,255));
        String captcha = getRandomString(7);
        g2.drawString(captcha, 20,25);

        HttpSession session = request.getSession();
        session.setAttribute("captcha" , captcha);

        try {
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpeg", outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getRandomString(int length) {
        String str = "abcdefghjklmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(60);
            sb.append(str.charAt(number) + " ");
        }
        return sb.toString();


    }

}
