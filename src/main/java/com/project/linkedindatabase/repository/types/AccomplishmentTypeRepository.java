package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.AccomplishmentTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccomplishmentTypeRepository extends BaseTypeRepository<AccomplishmentType> implements AccomplishmentTypeService {
    public AccomplishmentTypeRepository() throws SQLException {
        super(AccomplishmentType.class);
    }


    @Override
    public AccomplishmentType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public AccomplishmentType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(AccomplishmentType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<AccomplishmentType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(AccomplishmentType object) throws SQLException {
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
