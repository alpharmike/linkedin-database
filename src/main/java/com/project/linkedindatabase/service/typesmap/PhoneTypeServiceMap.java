package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.types.PhoneTypeRepository;
import com.project.linkedindatabase.service.types.PhoneTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PhoneTypeServiceMap extends PhoneTypeRepository implements PhoneTypeService {
    public PhoneTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public PhoneType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<PhoneType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByObject(PhoneType object) throws SQLException {
        super.deleteByObject(object);
    }
}