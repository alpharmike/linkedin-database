package com.project.linkedindatabase.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface BaseService<T, ID> {
    public T findById(ID id) throws Exception;

    public void save(T object) throws Exception;

    public List<T> findAll() throws Exception;

    public void deleteByObject(T object) throws Exception;

    public void deleteById(ID id) throws Exception;

    public void createTable() throws Exception;

    public  T convertSql(ResultSet resultSet);
}
