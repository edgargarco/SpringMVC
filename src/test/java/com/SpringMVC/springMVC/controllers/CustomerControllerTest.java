package com.SpringMVC.springMVC.controllers;

import com.SpringMVC.springMVC.models.Customer;
import com.SpringMVC.springMVC.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @Test
    public void testList() throws Exception{
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAllCustomer()).thenReturn((List) customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/l"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }
    @Test
    public void getCustomerByIdTest() throws Exception{
        Integer id = 1;
        when(customerService.getCustomerById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/1")).andExpect(status().isOk())
                .andExpect(view().name("customer"))
                .andExpect(model().attribute("customer",instanceOf(Customer.class)));
    }
    @Test
    public void deleteCustomerTest() throws Exception{
        Integer id = 1;
        mockMvc.perform(get("/customer/delete/1")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"));
        verify(customerService,times(1)).deleteCostumer(id);
    }
    @Test
    public void newCustomerTest() throws Exception{
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/customer/new")).andExpect(view().name("customerform"))
                .andExpect(status().isOk()).andExpect(model().attribute("customer",instanceOf(Customer.class)));
    }



}
