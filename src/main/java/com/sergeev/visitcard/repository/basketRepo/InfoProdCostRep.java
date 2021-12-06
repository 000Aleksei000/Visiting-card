package com.sergeev.visitcard.repository.basketRepo;

import com.sergeev.visitcard.data.basket.Costumer;
import com.sergeev.visitcard.data.basket.InfoProdCost;
import com.sergeev.visitcard.data.basket.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoProdCostRep extends JpaRepository<InfoProdCost, Long> {
    InfoProdCost getByCostumerAndProduct(Costumer costumer, Product product);
}
