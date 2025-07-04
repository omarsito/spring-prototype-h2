package com.piolin.spring.prototype.controller;

import com.piolin.spring.prototype.business.ClientBusiness;
import com.piolin.spring.prototype.database.entity.Client;
import com.piolin.spring.prototype.pojo.Deleted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//ready for use by Spring MVC to handle web requests
//Combines @Controller & @ResponseBody
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientBusiness clientBusiness;

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getClients(){
        List<Client> clients = clientBusiness.getClients();
        if (clients != null && !clients.isEmpty()){
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(clients, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){ //@RequestParams is for query params
        LOG.info("Client ID to get from DB: {}", id);
        Client client = clientBusiness.getClientById(id);
        if (client != null){
            return new ResponseEntity<>(client, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> insertClient(@RequestBody Client client){
        return new ResponseEntity<>(clientBusiness.createClient(client), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        return new ResponseEntity<>(clientBusiness.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deleted> deleteClient(@PathVariable("id") Long id){
        LOG.info("Client ID to delete from DB: {}", id);
        Deleted deleted = new Deleted(id, clientBusiness.deleteClientById(id));
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}