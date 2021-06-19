package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ShowPostTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ShowPostTypeRepository extends BaseTypeRepository<ShowPostType> implements ShowPostTypeService {
    public ShowPostTypeRepository() throws SQLException {
        super(ShowPostType.class);
    }

    @Override
    public ShowPostType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public ShowPostType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(ShowPostType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<ShowPostType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(ShowPostType object) throws SQLException {
        super.deleteByObject(object);
    }


    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }


    @Override
    public void createTable() throws SQLException {
        super.createTable();
    }
}
