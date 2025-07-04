package com.piolin.spring.prototype.database.repo;

import com.piolin.spring.prototype.database.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(long id);
}