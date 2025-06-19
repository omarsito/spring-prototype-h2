package com.piolin.spring.prototype.db.repo;

import com.piolin.spring.prototype.db.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(long id);
}