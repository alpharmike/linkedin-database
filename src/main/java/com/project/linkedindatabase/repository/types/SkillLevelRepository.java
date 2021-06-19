package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.SkillLevelService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SkillLevelRepository extends BaseTypeRepository<SkillLevel> implements SkillLevelService {
    public SkillLevelRepository() throws SQLException {
        super(SkillLevel.class);
    }



    @Override
    public SkillLevel convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public SkillLevel findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(SkillLevel object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<SkillLevel> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(SkillLevel object) throws SQLException {
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
