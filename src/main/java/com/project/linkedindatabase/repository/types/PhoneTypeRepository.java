package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.PhoneTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class PhoneTypeRepository extends BaseTypeRepository<PhoneType> implements PhoneTypeService {
    public PhoneTypeRepository() throws SQLException {
        super(PhoneType.class);
    }


    @Override
    public PhoneType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public PhoneType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(PhoneType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<PhoneType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(PhoneType object) throws SQLException {
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
