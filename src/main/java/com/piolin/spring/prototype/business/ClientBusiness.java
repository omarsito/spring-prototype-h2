package com.piolin.spring.prototype.business;

import com.piolin.spring.prototype.database.dao.ClientDao;
import com.piolin.spring.prototype.database.entity.Client;
import com.piolin.spring.prototype.pojo.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ClientBusiness {

    @Autowired
    ClientDao clientDao;

    public List<Client> getClients(){
        return clientDao.getClients();
    }

    public Client getClientById(Long id){
        return clientDao.getClientById(id);
    }

    public Client createClient(Client client){
        return clientDao.insertClient(client);
    }

    public Client updateClient(Client client){
        return clientDao.updateClient(client);
    }

    public boolean deleteClientById(Long id){
        return clientDao.deleteClientById(id);
    }

}