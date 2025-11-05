package com.piolin.spring.prototype.business;

import com.piolin.spring.prototype.database.dao.CustomerDao;
import com.piolin.spring.prototype.database.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerBusiness {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getClients(){
        return customerDao.getClients();
    }

    public Customer getClientById(Long id){
        return customerDao.getClientById(id);
    }

    public Customer createClient(Customer customer){
        return customerDao.insertClient(customer);
    }

    public Customer updateClient(Customer customer){
        return customerDao.updateClient(customer);
    }

    public boolean deleteClientById(Long id){
        return customerDao.deleteClientById(id);
    }

}