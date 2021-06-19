package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ConnectTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConnectTypeRepository extends BaseTypeRepository<ConnectType> implements ConnectTypeService {
    public ConnectTypeRepository() throws SQLException {
        super(ConnectType.class);
    }


    @Override
    public ConnectType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public ConnectType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(ConnectType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<ConnectType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(ConnectType object) throws SQLException {
        super.deleteByObject(object);
    }


    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }


    @Override
    public void createTable() throws SQLException {
        super.createTable();
    }
}
