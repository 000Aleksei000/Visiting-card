package com.sergeev.visitcard.web.model.crud;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sergeev.visitcard.data.crud.Country;
import com.sergeev.visitcard.data.crud.Town;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbInfoReq {

    String [] towns;

    String [] countries;

    public DbInfoReq(List<Town> townList, List<Country> countryList) {
        towns = new String[townList.size()];
        countries = new String[countryList.size()];
        for (int i = 0; i < towns.length; i++) {
            towns[i] = townList.get(i).getName();
        }

        for (int i = 0; i < countries.length; i++) {
            countries[i] = countryList.get(i).getName();
        }
    }

}
