package com.sergeev.visitcard.repository.basketRepo;

import com.sergeev.visitcard.data.basket.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRep extends JpaRepository<Costumer, Long> {
    Costumer getByCookie(String cookie);
}
