package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.connection.DataSourceConnector;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.BaseType;
import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.service.BaseService;
import com.project.linkedindatabase.utils.AnnotationValueGetter;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.*;

@Getter
@Setter
public abstract class BaseRepository<T extends BaseEntity, ID extends Long>   {

    protected Connection conn = DataSourceConnector.establishConnection();

    protected String tableName;

    public void dropTable() throws SQLException {
        final String createQuery = String.format("DROP TABLE %s;", this.tableName);
        PreparedStatement ps = conn.prepareStatement(createQuery);
        ps.execute();
    }

    public BaseRepository(Class<?> type) throws SQLException {
        this.tableName = AnnotationValueGetter.getTableName(type);
    }



    public T findById(ID id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName()+" where id = ?");

        ps.setLong(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (!resultSet.isBeforeFirst())
        {
            return null;
        }
        resultSet.next();
        return convertSql(resultSet);
    }


    public List<T> findAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName());


        ResultSet resultSet = ps.executeQuery();
        List<T> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }

    public void deleteByObject(T object) throws SQLException {
        this.deleteById((ID) object.getId());
    }

    public void deleteById(ID id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE  from "+this.getTableName()+" where id = ?");

        ps.setLong(1, id);
        ResultSet resultSet = ps.executeQuery();
    }

    abstract public void save(T object) throws SQLException;

    abstract public void createTable() throws SQLException;

    abstract public T convertSql(ResultSet resultSet) throws SQLException;
}


