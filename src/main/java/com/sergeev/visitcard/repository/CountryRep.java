package com.sergeev.visitcard.repository;

import com.sergeev.visitcard.data.crud.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRep extends CrudRepository<Country, Long> {
    public Country getCountryByName(String name);

    public List<Country> getCountryByIdAfter(Long id);


}
