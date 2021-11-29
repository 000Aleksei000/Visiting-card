package com.sergeev.visitcard.repository;

import com.sergeev.visitcard.data.People;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRep extends CrudRepository<People, Long> {

}
