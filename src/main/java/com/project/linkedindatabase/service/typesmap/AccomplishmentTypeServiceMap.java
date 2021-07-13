package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.repository.types.AccomplishmentTypeRepository;
import com.project.linkedindatabase.service.types.AccomplishmentTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentTypeServiceMap  implements AccomplishmentTypeService {

    private final AccomplishmentTypeRepository accomplishmentTypeRepository;

    public AccomplishmentTypeServiceMap() throws SQLException {
        this.accomplishmentTypeRepository = new AccomplishmentTypeRepository();
    }

    @Override
    public AccomplishmentType findById(Long id) throws SQLException {
        return accomplishmentTypeRepository.findById(id);
    }

    @Override
    public void save(AccomplishmentType object) throws SQLException {
        accomplishmentTypeRepository.save(object);
    }

    @Override
    public List<AccomplishmentType> findAll() throws SQLException {
        return accomplishmentTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        accomplishmentTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        accomplishmentTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(AccomplishmentType object) throws SQLException {
        accomplishmentTypeRepository.deleteByObject(object);
    }

    @Override
    public void saveIfNotExist(String name) throws SQLException {
        accomplishmentTypeRepository.saveIfNotExist(name);
    }

    @Override
    public AccomplishmentType findByName(String name) throws SQLException {
        return accomplishmentTypeRepository.findByName(name);
    }
}
