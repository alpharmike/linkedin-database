package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.types.PhoneTypeRepository;
import com.project.linkedindatabase.service.types.PhoneTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PhoneTypeServiceMap  implements PhoneTypeService {

    private final PhoneTypeRepository phoneTypeRepository;

    public PhoneTypeServiceMap() throws SQLException {
        phoneTypeRepository = new PhoneTypeRepository();
    }

    @Override
    public PhoneType findById(Long id) throws SQLException {
        return phoneTypeRepository.findById(id);
    }

    @Override
    public void save(PhoneType object) throws SQLException {
        phoneTypeRepository.save(object);
    }

    @Override
    public List<PhoneType> findAll() throws SQLException {
        return phoneTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        phoneTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        phoneTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(PhoneType object) throws SQLException {
        phoneTypeRepository.deleteByObject(object);
    }

    @Override
    public void saveIfNotExist(String name) throws SQLException {
        phoneTypeRepository.saveIfNotExist(name);

    }

    @Override
    public PhoneType findByName(String name) throws SQLException {
        return phoneTypeRepository.findByName(name);
    }

    @Override
    public PhoneType defaultType() throws SQLException {
        return phoneTypeRepository.defaultType();
    }
}