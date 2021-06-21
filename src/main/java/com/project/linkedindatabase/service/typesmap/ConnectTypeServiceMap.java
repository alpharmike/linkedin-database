package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.repository.types.ConnectTypeRepository;
import com.project.linkedindatabase.service.types.ConnectTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ConnectTypeServiceMap extends ConnectTypeRepository implements ConnectTypeService {
    public ConnectTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public ConnectType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<ConnectType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByObject(ConnectType object) throws SQLException {
        super.deleteByObject(object);
    }
}