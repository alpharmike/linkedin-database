package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.connection.DataSourceConnector;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.*;

@Getter
@Setter
public abstract class BaseRepository<T extends BaseEntity, ID extends Long> {

    Connection conn = DataSourceConnector.establishConnection();

    private final String tableName;

    public BaseRepository(String tableName) throws SQLException {
        this.tableName = tableName;

    }

    public abstract T convertSql(ResultSet resultSet);


    T findById(ID id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from ? where ? = id");
        ps.setString(1, tableName);
        ps.setLong(2, id);
        ResultSet resultSet = ps.executeQuery();
        return convertSql(resultSet);
    }


    List<T> findAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from ?");
        ps.setString(1, tableName);

        ResultSet resultSet = ps.executeQuery();
        List<T> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }

    void deleteByItem(T object) throws SQLException {
        this.deleteById((ID) object.getId());
    }

    void deleteById(ID id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE  from ? where id = ?");
        ps.setString(1, tableName);
        ps.setLong(2, id);
        ResultSet resultSet = ps.executeQuery();
    }

    abstract void insert(T object) throws SQLException;
}


