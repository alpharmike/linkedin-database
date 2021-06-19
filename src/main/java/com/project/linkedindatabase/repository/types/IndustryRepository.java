package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.IndustryService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class IndustryRepository extends BaseTypeRepository<Industry> implements IndustryService {
    public IndustryRepository() throws SQLException {
        super(Industry.class);
    }


    @Override
    public Industry convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public Industry findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(Industry object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<Industry> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(Industry object) throws SQLException {
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
