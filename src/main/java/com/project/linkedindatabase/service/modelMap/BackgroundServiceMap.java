package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.repository.model.BackgroundRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundServiceMap  implements BackgroundService {

    private final BackgroundRepository backgroundRepository;

    public BackgroundServiceMap() throws SQLException {
        this.backgroundRepository = new BackgroundRepository();
    }

    @Override
    public Background findById(Long aLong) throws SQLException {
        return backgroundRepository.findById(aLong);
    }

    @Override
    public void save(Background object) throws SQLException {
        backgroundRepository.save(object);
    }

    @Override
    public List<Background> findAll() throws SQLException {
        return backgroundRepository.findAll();
    }

    @Override
    public void deleteByObject(Background object) throws SQLException {
        backgroundRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        backgroundRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        backgroundRepository.createTable();
    }
}