package com.project.linkedindatabase.service;

import java.util.List;


public interface BaseService <T,ID>{
    T findById(ID  id);

    T save(T object);

    List<T> findAll();

    void deleteByObject(T object);

    void deleteById(ID id);
}
