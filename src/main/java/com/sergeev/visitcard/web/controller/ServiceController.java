package com.sergeev.visitcard.web.controller;


import com.sergeev.visitcard.service.MappingService;
import com.sergeev.visitcard.web.model.pg2.PeopleInfoReq;
import com.sergeev.visitcard.web.model.pg2.DbInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("service")
public class ServiceController {

    MappingService mappingService;

    @Autowired
    public ServiceController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @PutMapping(value = "/savePeople" , produces = "application/json;charset=UTF-8")
    public DbInfoReq savePeople(HttpServletRequest request ,@RequestBody PeopleInfoReq peopleInfoReq) {
        System.out.println(request.getRequestedSessionId());
        mappingService.savePeopleFromWeb(peopleInfoReq.getName(), peopleInfoReq.getLastName(), peopleInfoReq.getAge(), peopleInfoReq.getTown(), peopleInfoReq.getCountry());
        return constructResponse();
    }


    public DbInfoReq constructResponse() {
        DbInfoReq dbInfoReq = new DbInfoReq(mappingService.getTowns() , mappingService.getCountries());
        return dbInfoReq;
    }
}
