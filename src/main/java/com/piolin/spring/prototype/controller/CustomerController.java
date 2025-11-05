package com.piolin.spring.prototype.controller;

import com.piolin.spring.prototype.business.CustomerBusiness;
import com.piolin.spring.prototype.database.entity.Customer;
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
public class CustomerController {

    @Autowired
    CustomerBusiness customerBusiness;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getClients(){
        List<Customer> customers = customerBusiness.getClients();
        if (customers != null && !customers.isEmpty()){
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(customers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getClientById(@PathVariable("id") Long id){ //@RequestParams is for query params
        LOG.info("Client ID to get from DB: {}", id);
        Customer customer = customerBusiness.getClientById(id);
        if (customer != null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> insertClient(@RequestBody Customer customer){
        return new ResponseEntity<>(customerBusiness.createClient(customer), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateClient(@RequestBody Customer customer){
        return new ResponseEntity<>(customerBusiness.updateClient(customer), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deleted> deleteClient(@PathVariable("id") Long id){
        LOG.info("Client ID to delete from DB: {}", id);
        Deleted deleted = new Deleted(id, customerBusiness.deleteClientById(id));
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}