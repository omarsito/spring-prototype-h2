package com.piolin.spring.prototype.controller;

import com.piolin.spring.prototype.business.ClientBusiness;
import com.piolin.spring.prototype.entity.Client;
import jakarta.websocket.server.PathParam;
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
public class MainController {

    @Autowired
    ClientBusiness clientBusiness;

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("SpingBoot Application is Up & Running ...",HttpStatus.OK);
    }

    @GetMapping(value = "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getClients(){
        return new ResponseEntity<>(clientBusiness.getClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@PathParam("id") Long id){
        LOG.info("Client ID to get from DB: {}", id);
        return new ResponseEntity<>(clientBusiness.getClientById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/clients/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> insertClient(@RequestBody Client client){
        return new ResponseEntity<>(clientBusiness.insertClient(client), HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        return new ResponseEntity<>(clientBusiness.updateClient(client), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/clients/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteClient(@PathParam("id") Long id){
        LOG.info("Client ID to delete from DB: {}", id);
        return new ResponseEntity<>(clientBusiness.deleteClientById(id), HttpStatus.CREATED);
    }

}