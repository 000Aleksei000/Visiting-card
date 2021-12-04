package com.sergeev.visitcard.web.controller;


import com.google.gson.Gson;
import com.sergeev.visitcard.data.Country;
import com.sergeev.visitcard.data.People;
import com.sergeev.visitcard.data.Town;
import com.sergeev.visitcard.service.MappingService;
import com.sergeev.visitcard.web.model.pg2.PeopleInfoReq;
import com.sergeev.visitcard.web.model.pg2.DbInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("service")
public class ServiceController {

    MappingService mappingService;

    @Autowired
    public ServiceController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @PutMapping(value = "/savePeople" , produces = "application/json;charset=UTF-8")
    public DbInfoReq savePeople(@RequestBody PeopleInfoReq peopleInfoReq) {
        System.out.println(peopleInfoReq);
        mappingService.savePeopleFromWeb(peopleInfoReq.getName(), peopleInfoReq.getLastName(), peopleInfoReq.getAge(), peopleInfoReq.getTown(), peopleInfoReq.getCountry());
        return constructResponse();
    }


    public DbInfoReq constructResponse() {
        DbInfoReq dbInfoReq = new DbInfoReq(mappingService.getTowns() , mappingService.getCountries());
        return dbInfoReq;
    }
}
