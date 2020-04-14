package com.SpringMVC.springMVC.bootstrap;

import com.SpringMVC.springMVC.models.Product;
import com.SpringMVC.springMVC.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductService productService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
    }

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void loadProducts() {

        Product product1 = new Product();
        product1.setId(1);
        product1.setPrice(new BigDecimal("15.99"));
        product1.setDescription("Product 1");
        product1.setImageUrl("https://picsum.photos/200");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setPrice(new BigDecimal("15.99"));
        product2.setDescription("Product 2");
        product2.setImageUrl("https://picsum.photos/200");
        productService.saveOrUpdate(product2);

    }
}
