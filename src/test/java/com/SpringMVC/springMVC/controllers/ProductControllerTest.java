package com.SpringMVC.springMVC.controllers;


import com.SpringMVC.springMVC.models.Product;
import com.SpringMVC.springMVC.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {
    @Mock //Mockito mock object
    private ProductService productService;
    @InjectMocks // Setups up controller, and ijects mock
    private ProductController productController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        when(productService.listAllProduct()).thenReturn((List) products);
        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(view()
                .name("products/l")).andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        //Tell Mockito stub to return new product for ID 1
        when(productService.getProductById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/1"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;
        //Tell Mockito stub to return new product for ID 1
        when(productService.getProductById(id)).thenReturn(new Product());
        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }
    @Test
    public void testNewProduct() throws Exception{
        verifyZeroInteractions(productService);
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception{
        Integer id = 1;
        String description = "Test Description";
        BigDecimal price = new BigDecimal("12.03");
        String imageUrl = "example.url";
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        when(productService.saveOrUpdateProduct(Matchers.<Product>any())).thenReturn(product);
        mockMvc.perform(post("/product").param("id", String.valueOf(id))
        .param("description",description).param("price", String.valueOf(price)).param("imageUrl",imageUrl))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/product/1"))
        .andExpect(model().attribute("product", instanceOf(Product.class)))
        .andExpect(model().attribute("product",hasProperty("id",is(id))))
                .andExpect(model().attribute("product",hasProperty("description",is(description))))
                .andExpect(model().attribute("product",hasProperty("price",is(price))))
                .andExpect(model().attribute("product",hasProperty("imageUrl",is(imageUrl))));

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdateProduct(productArgumentCaptor.capture());
        assertEquals(id,productArgumentCaptor.getValue().getId());
        assertEquals(description,productArgumentCaptor.getValue().getDescription());
        assertEquals(imageUrl,productArgumentCaptor.getValue().getImageUrl());
        assertEquals(price,productArgumentCaptor.getValue().getPrice());

    }

    @Test
    public void deleteProduct() throws Exception{
        Integer id = 1;
        mockMvc.perform(get("/product/delete/1")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"));
        verify(productService,times(1)).deleteProduct(id);
    }



}
