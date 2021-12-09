package com.sergeev.visitcard.web.controller;

import com.sergeev.visitcard.service.basketService.BasketService;
import com.sergeev.visitcard.web.model.bucket.AddProductToBasketReq;
import com.sergeev.visitcard.web.model.bucket.Product;
import com.sergeev.visitcard.web.model.bucket.ProductsListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("basketServ")
public class BasketServController {

    private BasketService basketService;

    @Autowired
    public BasketServController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/addProduct")
    public void addProduct() {
            basketService.addProduct("Cucumber", 80);
            basketService.addProduct("Apple", 100);
            basketService.addProduct("Orange", 120);
            basketService.addProduct("Potato", 30);
            basketService.addProduct("Cherry", 300);
            basketService.addProduct("strawberry", 250);
            basketService.addProduct("pineapple", 500);
            basketService.addProduct("kiwi", 180);
            basketService.addProduct("mango", 200);
    }


    @PostMapping(value = "/addProductToCostumer", produces = "application/json")
    public void addProductToCostumer(HttpServletRequest request, @RequestBody AddProductToBasketReq req) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookieForBasket")) {
                basketService.addProductToCostumer(cookie.getValue(), req.getName(), req.getQuantity());
            }
        }
    }

    @GetMapping(value = "/getProductsList")
    public ProductsListResp getProductsList() {
        ProductsListResp resp = new ProductsListResp();
        resp.fillAllProduct(basketService);
        return resp;
    }

    @GetMapping(value = "/getProductsByCookie")
    public ProductsListResp getProductsByCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ProductsListResp resp = new ProductsListResp();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookieForBasket")) {
                resp.fillProductsByCookie(basketService,cookie.getValue());
            }
        }
        return resp;
    }

    @PostMapping(value = "deleteFromBasket", consumes = "application/json")
    public void deleteFromBasket(HttpServletRequest request, @RequestBody Product product) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookieForBasket")) {
                basketService.deleteFromBasket(cookie.getValue(), product.getName());
            }
        }
    }
}
