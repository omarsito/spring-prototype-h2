package com.piolin.spring.prototype.repos;

import com.piolin.spring.prototype.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(long id);
}