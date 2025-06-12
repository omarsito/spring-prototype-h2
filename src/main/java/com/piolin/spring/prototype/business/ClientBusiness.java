package com.piolin.spring.prototype.business;

import com.piolin.spring.prototype.entity.Client;
import com.piolin.spring.prototype.repos.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientBusiness {

    private static final Logger LOG = LoggerFactory.getLogger(ClientBusiness.class);

    ClientRepository clientRepository;

    public Client insertClient(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public boolean deleteClientById(long id){
        try {
            clientRepository.deleteById(id);
        }catch (Exception ex){
            LOG.error("Error deleting Client, ID: {}", id);

            LOG.error("Error deleting Client, error: {}", ex.getMessage());
        }
        return true;
    }

    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(client ->
                LOG.info(client.toString())
        );
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }
    public Client getClientById(long id){
        return clientRepository.findById(id);
    }
}
