package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.types.ShowPostTypeRepository;
import com.project.linkedindatabase.service.types.ShowPostTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ShowPostTypeServiceMap extends ShowPostTypeRepository implements ShowPostTypeService {
    public ShowPostTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public ShowPostType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<ShowPostType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByItem(ShowPostType object) throws SQLException {
        super.deleteByItem(object);
    }
}