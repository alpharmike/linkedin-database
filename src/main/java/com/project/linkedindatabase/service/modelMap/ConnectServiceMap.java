package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.repository.model.ConnectRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ConnectServiceMap implements ConnectService {

    private final ConnectRepository connectRepository;

    public ConnectServiceMap() throws SQLException {
        this.connectRepository = new ConnectRepository();
    }

    @Override
    public Connect findById(Long aLong) throws SQLException {
        return connectRepository.findById(aLong);
    }

    @Override
    public void save(Connect object) throws SQLException {
        connectRepository.save(object);
    }

    @Override
    public List<Connect> findAll() throws SQLException {
        return connectRepository.findAll();
    }

    @Override
    public void deleteByObject(Connect object) throws SQLException {
        connectRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        connectRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        connectRepository.createTable();
    }
}
