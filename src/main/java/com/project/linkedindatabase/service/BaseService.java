package com.project.linkedindatabase.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface BaseService<T, ID> {
    public T findById(ID id) throws SQLException;

    public void save(T object) throws SQLException;

    public List<T> findAll() throws SQLException;

    public void deleteByObject(T object) throws SQLException;

    public void deleteById(ID id) throws SQLException;


    public void createTable() throws SQLException;
}
