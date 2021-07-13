package com.project.linkedindatabase.service;

import com.project.linkedindatabase.domain.Type.BaseType;

import java.sql.SQLException;

public interface BaseTypeService<T> extends BaseService<T, Long> {
    public void saveIfNotExist(String name) throws SQLException;
    public T findByName(String name) throws SQLException;
}
