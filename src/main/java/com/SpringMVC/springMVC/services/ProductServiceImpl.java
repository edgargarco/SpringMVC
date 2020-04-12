package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {


    private Map<Integer, Product> productsMap;


    public ProductServiceImpl() {
        loadProducts();
    }

    @Override
    public List<Product> listAllProduct() {
        return new ArrayList<>(productsMap.values());
    }

    @Override
    public Product getProductById(Integer id) {
        return productsMap.get(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        productsMap.remove(id);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (product.getId() == null) {
                product.setId(getNextKey());
            }
            productsMap.put(product.getId(), product);
            return product;
        } else {
            throw new RuntimeException("Product Can't be nill");
        }
    }

    private Integer getNextKey() {
        if (!productsMap.isEmpty()) {
            return Collections.max(productsMap.keySet()) + 1;
        } else {
            return 1;
        }
    }

    private void loadProducts() {
        productsMap = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setPrice(new BigDecimal("15.99"));
        product1.setDescription("Product 1");
        product1.setImageUrl("https://picsum.photos/200");
        productsMap.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setPrice(new BigDecimal("15.99"));
        product2.setDescription("Product 2");
        product2.setImageUrl("https://picsum.photos/200");
        productsMap.put(2, product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setPrice(new BigDecimal("15.99"));
        product3.setDescription("Product 3");
        product3.setImageUrl("https://picsum.photos/200");
        productsMap.put(3, product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setPrice(new BigDecimal("15.99"));
        product4.setDescription("Product 4");
        product4.setImageUrl("https://picsum.photos/200");
        productsMap.put(4, product4);

    }
}
