package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.LanguageLevelService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageLevelRepository extends BaseTypeRepository<LanguageLevel> implements LanguageLevelService {
    public LanguageLevelRepository() throws SQLException {
        super(LanguageLevel.class);
    }


    @Override
    public LanguageLevel convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public LanguageLevel findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(LanguageLevel object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<LanguageLevel> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(LanguageLevel object) throws SQLException {
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
