package com.SpringMVC.springMVC.controllers;

import com.SpringMVC.springMVC.models.Customer;
import com.SpringMVC.springMVC.models.Product;
import com.SpringMVC.springMVC.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listCustomer(Model model){
        model.addAttribute("customers",customerService.listAll());
        return "customers";
    }
    @RequestMapping("/customer/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model){
        model.addAttribute("customer",customerService.getById(id));
        return "customer";
    }
    @RequestMapping("/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "customerform";
    }
    @RequestMapping(value = "/customer",method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer){
        Customer saveOrUpdateCustomer = customerService.saveOrUpdate(customer);
        return "redirect:/customer/"+saveOrUpdateCustomer.getId();

    }
    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer",customerService.getById(id));
        return "customerform";
    }
    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers";
    }
}
