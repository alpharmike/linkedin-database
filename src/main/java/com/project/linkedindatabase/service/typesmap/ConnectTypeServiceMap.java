package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.repository.types.ConnectTypeRepository;
import com.project.linkedindatabase.service.types.ConnectTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ConnectTypeServiceMap implements ConnectTypeService {

    private final ConnectTypeRepository connectTypeRepository;

    public ConnectTypeServiceMap() throws SQLException {
        connectTypeRepository = new ConnectTypeRepository();
    }

    @Override
    public ConnectType findById(Long id) throws SQLException {
        return connectTypeRepository.findById(id);
    }

    @Override
    public void save(ConnectType object) throws SQLException {
        connectTypeRepository.save(object);
    }

    @Override
    public List<ConnectType> findAll() throws SQLException {
        return connectTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        connectTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        connectTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(ConnectType object) throws SQLException {
        connectTypeRepository.deleteByObject(object);
    }

    @Override
    public void saveIfNotExist(String name) throws SQLException {
        connectTypeRepository.saveIfNotExist(name);

    }

    @Override
    public ConnectType findByName(String name) throws SQLException {
        return connectTypeRepository.findByName(name);

    }

    @Override
    public ConnectType defaultType() throws SQLException {
        return connectTypeRepository.defaultType();
    }
}