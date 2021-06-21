package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.repository.types.BackgroundTypeRepository;
import com.project.linkedindatabase.service.types.BackgroundTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundTypeServiceMap implements BackgroundTypeService {

    private final BackgroundTypeRepository backgroundTypeRepository;

    public BackgroundTypeServiceMap() throws SQLException {
        backgroundTypeRepository = new BackgroundTypeRepository();
    }

    @Override
    public BackgroundType findById(Long id) throws SQLException {
        return backgroundTypeRepository.findById(id);
    }

    @Override
    public void save(BackgroundType object) throws SQLException {
        backgroundTypeRepository.save(object);
    }

    @Override
    public List<BackgroundType> findAll() throws SQLException {
        return backgroundTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        backgroundTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        backgroundTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(BackgroundType object) throws SQLException {
        backgroundTypeRepository.deleteByObject(object);
    }
}