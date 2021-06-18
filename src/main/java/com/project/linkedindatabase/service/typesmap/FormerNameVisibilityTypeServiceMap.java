package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.repository.types.FormerNameVisibilityTypeRepository;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FormerNameVisibilityTypeServiceMap extends FormerNameVisibilityTypeRepository implements FormerNameVisibilityTypeService {
    public FormerNameVisibilityTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public FormerNameVisibilityType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<FormerNameVisibilityType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByItem(FormerNameVisibilityType object) throws SQLException {
        super.deleteByItem(object);
    }
}