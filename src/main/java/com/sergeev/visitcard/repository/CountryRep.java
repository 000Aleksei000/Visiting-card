package com.sergeev.visitcard.repository;

import com.sergeev.visitcard.data.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRep extends CrudRepository<Country, Long> {
    public Country getCountryByName(String name);
}
