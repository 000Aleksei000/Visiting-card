package com.sergeev.visitcard.web.model.bucket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sergeev.visitcard.data.basket.Costumer;
import com.sergeev.visitcard.data.basket.InfoProdCost;
import com.sergeev.visitcard.service.basketService.BasketService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsListResp {
    private List<Product> products;

//    public ProductsListResp(BasketService basketService) {
//        products = new ArrayList<>();
//        for (com.sergeev.visitcard.data.basket.Product product : basketService.getProductsList()) {
//            Product product1 = new Product();
//            product1.setName(product.getProdName());
//            product1.setPrice(product.getPrice());
//            products.add(product1);
//        }
//        ;
//    }


    public void fillAllProduct(BasketService basketService){
        products = new ArrayList<>();
        for (com.sergeev.visitcard.data.basket.Product product : basketService.getProductsList()) {
            Product product1 = new Product();
            product1.setName(product.getProdName());
            product1.setPrice(product.getPrice());
            products.add(product1);
        }
    }

    public void fillProductsByCookie(BasketService basketService, String cookie) {
        products = new ArrayList<>();
        Costumer costumer = basketService.getCostumerByCookie(cookie);
        for(InfoProdCost infoProdCost : costumer.getInfoProdCosts()){
            Product product = new Product();
            product.setQuantity(infoProdCost.getQuantity());
            product.setName(infoProdCost.getProduct().getProdName());
            product.setPrice(infoProdCost.getProduct().getPrice());
            products.add(product);
        }
    }
}
