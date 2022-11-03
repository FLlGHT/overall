package com.flight.overall.repository;

import com.flight.overall.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {


}
