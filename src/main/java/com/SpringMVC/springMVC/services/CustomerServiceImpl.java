package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.Customer;
import com.SpringMVC.springMVC.models.DomainObject;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService{


    @Override
    protected void loadDomainObjects() {
        integerDomainObjectMap = new HashMap<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Edgar");
        customer.setLastName("Garcia");
        customer.setAddressOne("Home");
        customer.setAddressTwo("Two");
        customer.setPhoneNumber("982-399-3342");
        customer.setEmail("example@spring.com");
        customer.setCity("Miami");
        customer.setState("Fl");
        customer.setZipCode("33195");
        integerDomainObjectMap.put(1,customer);

    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
