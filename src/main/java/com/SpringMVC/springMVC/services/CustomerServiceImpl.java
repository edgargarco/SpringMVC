package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.Customer;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<Integer,Customer> customerMap;

    public CustomerServiceImpl(){
        loadCustomer();
    }

    @Override
    public List<Customer> listAllCustomer() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerMap.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null){
            if (customer.getId() == null){
                customer.setId(getNextKey());
            }
            customerMap.put(customer.getId(),customer);
            return customer;
        }else {
            throw new RuntimeException("Product Can't be nill");
        }

    }
    private Integer getNextKey() {
        if (!customerMap.isEmpty()) {
            return Collections.max(customerMap.keySet()) + 1;
        } else {
            return 1;
        }
    }

    @Override
    public void deleteCostumer(Integer id) {
        customerMap.remove(id);

    }
    private void loadCustomer() {
        customerMap = new HashMap<>();

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
        customerMap.put(customer.getId(), customer);}
}
