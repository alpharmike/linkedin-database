package com.project.linkedindatabase.repository;

import com.fasterxml.jackson.databind.util.Converter;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.*;
@Getter
@Setter
public abstract class BaseRepository<T extends BaseEntity,ID extends  Long> {

    //todo
    //Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

    private String tableName;

    private final Converter<ResultSet, T> convertSql;

    public BaseRepository(String tableName, Converter<ResultSet, T> convertSql) throws SQLException {
        this.tableName = tableName;
        this.convertSql = convertSql;
    }


    T findById(ID  id) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select * from ? where ? = id");
        ps.setString(1,tableName);
        ps.setLong(2,id);
        ResultSet resultSet = ps.executeQuery();
        return convertSql.convert(resultSet);
    }


    List<T> findAll() throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from ?");
        ps.setString(1,tableName);

        ResultSet resultSet = ps.executeQuery();
        List<T> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql.convert(resultSet));
        }
        return allObject;
    }

    void deleteByItem(T object) throws SQLException {
        this.deleteById((ID) object.getId());
    }

    void deleteById(ID id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE  from ? where id = ?");
        ps.setString(1,tableName);
        ps.setLong(2,id);
        ResultSet resultSet = ps.executeQuery();
    }


}


