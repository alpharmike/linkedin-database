package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class FormerNameVisibilityTypeRepository extends BaseTypeRepository<FormerNameVisibilityType> implements FormerNameVisibilityTypeService {
    public FormerNameVisibilityTypeRepository() throws SQLException {
        super(FormerNameVisibilityType.class);
    }


    @Override
    public FormerNameVisibilityType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public FormerNameVisibilityType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(FormerNameVisibilityType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<FormerNameVisibilityType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(FormerNameVisibilityType object) throws SQLException {
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
