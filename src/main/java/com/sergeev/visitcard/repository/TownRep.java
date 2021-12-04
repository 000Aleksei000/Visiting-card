package com.sergeev.visitcard.repository;

import com.sergeev.visitcard.data.crud.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownRep extends JpaRepository<Town, Long> {

    public boolean existsByName(String name);

    public Town getTownByName(String name);

    public List<Town> getTownByIdAfter(Long id);

}
