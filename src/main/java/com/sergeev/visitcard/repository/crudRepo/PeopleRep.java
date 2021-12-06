package com.sergeev.visitcard.repository.crudRepo;

import com.sergeev.visitcard.data.crud.People;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRep extends CrudRepository<People, Long> {

}
