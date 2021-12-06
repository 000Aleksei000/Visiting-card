package com.sergeev.visitcard.repository.basketRepo;

import com.sergeev.visitcard.data.basket.Costumer;
import com.sergeev.visitcard.data.basket.InfoProdCost;
import com.sergeev.visitcard.data.basket.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InfoProdCostRep extends JpaRepository<InfoProdCost, Long> {
    InfoProdCost getByCostumerAndProduct(Costumer costumer, Product product);

    @Modifying
    @Query(value = "delete from InfoProdCost where id = :id")
    void deleteById(@Param("id") Long id);
}
