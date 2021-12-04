package com.sergeev.visitcard.web.model.pg2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.sergeev.visitcard.data.Country;
import com.sergeev.visitcard.data.Town;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
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
