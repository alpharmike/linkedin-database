package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.repository.types.AccomplishmentTypeRepository;
import com.project.linkedindatabase.service.types.AccomplishmentTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentTypeServiceMap extends AccomplishmentTypeRepository implements AccomplishmentTypeService {
    public AccomplishmentTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public AccomplishmentType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<AccomplishmentType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByObject(AccomplishmentType object) throws SQLException {
        super.deleteByItem(object);
    }
}
