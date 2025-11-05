package com.piolin.spring.prototype.database.repo;

import com.piolin.spring.prototype.database.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findById(long id);
}