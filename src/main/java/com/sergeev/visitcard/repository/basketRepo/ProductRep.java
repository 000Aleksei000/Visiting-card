package com.sergeev.visitcard.repository.basketRepo;

import com.sergeev.visitcard.data.basket.Costumer;
import com.sergeev.visitcard.data.basket.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRep extends JpaRepository<Product, Costumer> {
    public Product getByProdName(String name);

    List<Product> getAllByProdIdAfter(Long id);

}
