package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.BackgroundTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundTypeRepository extends BaseTypeRepository<BackgroundType> implements BackgroundTypeService {

    public BackgroundTypeRepository() throws SQLException {
        super(BackgroundType.class);
    }


    @Override
    public BackgroundType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public BackgroundType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(BackgroundType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<BackgroundType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(BackgroundType object) throws SQLException {
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
