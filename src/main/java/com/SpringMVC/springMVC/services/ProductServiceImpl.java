package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.DomainObject;
import com.SpringMVC.springMVC.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {


    @Override
    protected void loadDomainObjects() {
        integerDomainObjectMap = new HashMap<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setPrice(new BigDecimal("15.99"));
        product1.setDescription("Product 1");
        product1.setImageUrl("https://picsum.photos/200");
        integerDomainObjectMap.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setPrice(new BigDecimal("15.99"));
        product2.setDescription("Product 2");
        product2.setImageUrl("https://picsum.photos/200");
        integerDomainObjectMap.put(2, product2);

    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }


    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
