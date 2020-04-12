package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.Customer;
import com.SpringMVC.springMVC.models.Product;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomer();
    Customer getCustomerById(Integer id);
    Customer saveOrUpdateCustomer(Customer customer);
    void deleteCostumer(Integer id);
}
