package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.repository.types.BackgroundTypeRepository;
import com.project.linkedindatabase.service.types.BackgroundTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundTypeServiceMap extends BackgroundTypeRepository implements BackgroundTypeService {
    public BackgroundTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public BackgroundType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<BackgroundType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByItem(BackgroundType object) throws SQLException {
        super.deleteByItem(object);
    }
}