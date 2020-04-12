package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.Product;


import java.util.List;

public interface ProductService {
    List<Product> listAllProduct();
    Product getProductById(Integer id);
    Product saveOrUpdateProduct(Product product);
    void deleteProduct(Integer id);
}
