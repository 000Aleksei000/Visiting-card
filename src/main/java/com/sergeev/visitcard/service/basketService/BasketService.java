package com.sergeev.visitcard.service.basketService;


import com.sergeev.visitcard.data.basket.Costumer;
import com.sergeev.visitcard.data.basket.InfoProdCost;
import com.sergeev.visitcard.data.basket.Product;
import com.sergeev.visitcard.repository.basketRepo.CostumerRep;
import com.sergeev.visitcard.repository.basketRepo.InfoProdCostRep;
import com.sergeev.visitcard.repository.basketRepo.ProductRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BasketService {
    private CostumerRep costumerRep;
    private ProductRep productRep;
    private InfoProdCostRep infoProdCostRep;

    @Autowired
    public BasketService(CostumerRep costumerRep, ProductRep productRep, InfoProdCostRep infoProdCostRep) {
        this.costumerRep = costumerRep;
        this.productRep = productRep;
        this.infoProdCostRep = infoProdCostRep;
    }

    public void addProduct(String prodName, double price) {
        Product product = new Product();
        product.setProdName(prodName.toLowerCase());
        product.setPrice(price);
        Product productInDb = productRep.getByProdName(prodName);
        if (productInDb == null) {
            productRep.save(product);
        }
    }

    public void addCostumer(String cookie) {
        Costumer costumer = new Costumer();
        costumer.setCookie(cookie);
        costumer.setDate(new SimpleDateFormat("yyyy-dd-MM").format(new Date()));
        Costumer costumerInDb = costumerRep.getByCookie(cookie);
        if (costumerInDb == null) {
            costumerRep.save(costumer);
        }
    }

    public void addProductToCostumer(String cookie, String productName, int quantity) {
        Product product = productRep.getByProdName(productName);
        Costumer costumer = costumerRep.getByCookie(cookie);
        InfoProdCost infoProdCost = infoProdCostRep.getByCostumerAndProduct(costumer, product);
        if (infoProdCost == null) {
            infoProdCost = new InfoProdCost();
            infoProdCost.setQuantity(quantity);
            product.addInfoProdCost(infoProdCost);
            costumer.addInfoProdCost(infoProdCost);
        } else {
            infoProdCost.setQuantity(quantity);
            product.addInfoProdCost(infoProdCost);
            costumer.addInfoProdCost(infoProdCost);
        }


        infoProdCostRep.save(infoProdCost);
    }


    public List<Product> getProductsList() {
        return productRep.getAllByProdIdAfter(0L);
    }

    public Costumer getCostumerByCookie(String cookie) {
        return costumerRep.getByCookie(cookie);
    }
}
