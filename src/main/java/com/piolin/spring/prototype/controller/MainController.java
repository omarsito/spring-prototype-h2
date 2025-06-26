package com.piolin.spring.prototype.controller;

import com.piolin.spring.prototype.config.ConfigProperties;
import com.piolin.spring.prototype.db.dao.ClientDao;
import com.piolin.spring.prototype.db.entity.Client;
import com.piolin.spring.prototype.pojo.Deleted;
import com.piolin.spring.prototype.pojo.Root;
import com.piolin.spring.prototype.util.Cons;
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
    ConfigProperties appProps;

    @Autowired
    ClientDao clientDao;

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Root> index(){
        Root root = new Root(Cons.ROOT_ENDPOINT_MSG, appProps.getPropsValue("spring.application.version"));
        return new ResponseEntity<>(root, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getClients(){
        List<Client> clients = clientDao.getClients();
        if (clients != null && !clients.isEmpty()){
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(clients, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/clients/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){ //@RequestParams is for query params
        LOG.info("Client ID to get from DB: {}", id);
        Client client = clientDao.getClientById(id);
        if (client != null){
            return new ResponseEntity<>(client, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/clients/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> insertClient(@RequestBody Client client){
        return new ResponseEntity<>(clientDao.insertClient(client), HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        return new ResponseEntity<>(clientDao.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deleted> deleteClient(@PathVariable("id") Long id){
        LOG.info("Client ID to delete from DB: {}", id);
        Deleted deleted = new Deleted(id, clientDao.deleteClientById(id));
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}