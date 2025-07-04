package com.piolin.spring.prototype.database.dao;

import com.piolin.spring.prototype.database.entity.Client;
import com.piolin.spring.prototype.database.repo.ClientRepository;
import com.piolin.spring.prototype.util.Cons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private static final Logger LOG = LoggerFactory.getLogger(ClientDao.class);
    private static final DateFormat dateFormatter = new SimpleDateFormat(Cons.POSTGRES_DATE_FORMAT);

    @Autowired
    ClientRepository clientRepository;

    public Client insertClient(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public boolean deleteClientById(long id){
        boolean wasDeleted;
        try {
            if(clientRepository.findById(id) != null){
                clientRepository.deleteById(id);
                wasDeleted = true;
            }else{
                wasDeleted = false;
            }
        }catch (Exception ex){
            LOG.error("Error deleting Client, error: {}", ex.getMessage());
            wasDeleted = false;
        }
        return wasDeleted;
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
