package com.sergeev.visitcard.repository;

import com.sergeev.visitcard.data.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;

public interface TownRep extends JpaRepository<Town, Long> {

    public boolean existsByName(String name);

    public Town getTownByName(String name);

    public List<Town> getTownByIdAfter(Long id);

}
