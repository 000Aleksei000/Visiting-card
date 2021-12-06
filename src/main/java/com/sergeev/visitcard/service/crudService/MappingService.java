package com.sergeev.visitcard.service.crudService;

import com.sergeev.visitcard.data.crud.Country;
import com.sergeev.visitcard.data.crud.People;
import com.sergeev.visitcard.data.crud.Town;
import com.sergeev.visitcard.repository.crudRepo.CountryRep;
import com.sergeev.visitcard.repository.crudRepo.PeopleRep;
import com.sergeev.visitcard.repository.crudRepo.TownRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingService {

    CountryRep countryRep;
    PeopleRep peopleRep;
    TownRep townRep;

    @Autowired
    public MappingService(CountryRep countryRep, PeopleRep peopleRep, TownRep townRep) {
        this.countryRep = countryRep;
        this.peopleRep = peopleRep;
        this.townRep = townRep;
    }

    public void savePeople(People people) {
        peopleRep.save(people);
    }


    public Town getTown(String name) {
        Town townByName = townRep.getTownByName(name);
        if (townByName == null) {
            Town town = new Town();
            town.setName(name);
            return town;
        } else return townByName;
    }

    public Country getCountry(String name) {
        Country country = countryRep.getCountryByName(name);
        if (country == null) {
            country = new Country();
            country.setName(name);
            return country;
        } else return country;
    }


    public boolean savePeopleFromWeb(String name, String lastName, String age, String townPar, String countryPar) {
        People people = new People();
        people.setName(name);
        people.setLastName(lastName);
        people.setAge(Integer.parseInt(age));
        Town town = this.getTown(townPar);
        town.addPeople(people);
        Country country = this.getCountry(countryPar);
        country.addTown(town);
        country.addPeople(people);
        try {
            this.savePeople(people);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Town> getTowns() {
        List<Town> all = townRep.getTownByIdAfter(0L);
        return all;
    }

    public List<Country> getCountries() {
        return countryRep.getCountryByIdAfter(0L);
    }
}
