package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Type.BaseType;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.BaseService;
import com.project.linkedindatabase.service.BaseTypeService;
import com.project.linkedindatabase.utils.AnnotationValueGetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseTypeRepository<T extends BaseType> extends BaseRepository<T , Long>   {

    public BaseTypeRepository(Class<?> type) throws SQLException {
        super(type);
    }



    @Override
    public void save(T object) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into ? (name) values (?)");
        ps.setString(1, this.getTableName());
        ps.setString(2, object.getName());
        ResultSet resultSet = ps.executeQuery();
    }

    @Override
    public T findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<T> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }



    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("CREATE TABLE ? " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " +
                " PRIMARY KEY ( id ))");

        ps.setString(1,this.tableName);
        ps.execute();
    }


}
