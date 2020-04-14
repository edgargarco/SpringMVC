package com.SpringMVC.springMVC.services;

import com.SpringMVC.springMVC.models.DomainObject;

import java.util.*;

public abstract class AbstractMapService {
    protected Map<Integer, DomainObject> integerDomainObjectMap;
    public AbstractMapService(){
        integerDomainObjectMap = new HashMap<>();
        loadDomainObjects();
    }
    protected abstract void loadDomainObjects();

    public List<DomainObject> listAll(){
        return new ArrayList<>(integerDomainObjectMap.values());
    }
    public DomainObject getById(Integer id){
        return integerDomainObjectMap.get(id);
    }
    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if (domainObject != null){

            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            integerDomainObjectMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        integerDomainObjectMap.remove(id);
    }

    private Integer getNextKey(){
        return Collections.max(integerDomainObjectMap.keySet()) + 1;
    }
}
