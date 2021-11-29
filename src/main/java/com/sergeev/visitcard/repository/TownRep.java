package com.sergeev.visitcard.repository;

import com.sergeev.visitcard.data.Town;
import org.springframework.data.repository.CrudRepository;

public interface TownRep extends CrudRepository<Town, Long> {

    public boolean existsByName(String name);

    public Town getTownByName(String name);
}
