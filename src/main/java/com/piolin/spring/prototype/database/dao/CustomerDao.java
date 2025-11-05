package com.piolin.spring.prototype.database.dao;

import com.piolin.spring.prototype.database.entity.Customer;
import com.piolin.spring.prototype.database.repo.CustomerRepository;
import com.piolin.spring.prototype.util.Cons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDao.class);
    private static final DateFormat dateFormatter = new SimpleDateFormat(Cons.POSTGRES_DATE_FORMAT);

    @Autowired
    CustomerRepository customerRepository;

    public Customer insertClient(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateClient(Customer customer){
        return customerRepository.save(customer);
    }

    public boolean deleteClientById(long id){
        boolean wasDeleted;
        try {
            if(customerRepository.findById(id) != null){
                customerRepository.deleteById(id);
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

    public List<Customer> getClients() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customer ->
                LOG.info(customer.toString())
        );
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }
    public Customer getClientById(long id){
        return customerRepository.findById(id);
    }
}
