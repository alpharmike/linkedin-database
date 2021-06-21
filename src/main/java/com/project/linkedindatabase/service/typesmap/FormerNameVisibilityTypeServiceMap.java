package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.repository.types.FormerNameVisibilityTypeRepository;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FormerNameVisibilityTypeServiceMap  implements FormerNameVisibilityTypeService {

    private final FormerNameVisibilityTypeRepository formerNameVisibilityTypeRepository;

    public FormerNameVisibilityTypeServiceMap() throws SQLException {
        formerNameVisibilityTypeRepository = new FormerNameVisibilityTypeRepository();
    }

    @Override
    public FormerNameVisibilityType findById(Long id) throws SQLException {
        return formerNameVisibilityTypeRepository.findById(id);
    }

    @Override
    public void save(FormerNameVisibilityType object) throws SQLException {
        formerNameVisibilityTypeRepository.save(object);
    }

    @Override
    public List<FormerNameVisibilityType> findAll() throws SQLException {
        return formerNameVisibilityTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        formerNameVisibilityTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        formerNameVisibilityTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(FormerNameVisibilityType object) throws SQLException {
        formerNameVisibilityTypeRepository.deleteByObject(object);
    }
}