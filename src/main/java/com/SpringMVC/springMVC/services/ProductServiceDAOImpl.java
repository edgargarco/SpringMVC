package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpaDAO")
public class ProductServiceDAOImpl implements ProductService {
    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<?> listAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product product = entityManager.merge(domainObject);
        entityManager.getTransaction().commit();

        entityManager.close();
        return product;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Product.class, id));
        entityManager.getTransaction().commit();
    }
}
