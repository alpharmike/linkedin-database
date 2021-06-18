package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.repository.types.LanguageLevelRepository;
import com.project.linkedindatabase.service.types.LanguageLevelService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageLevelServiceMap extends LanguageLevelRepository implements LanguageLevelService {
    public LanguageLevelServiceMap() throws SQLException {
        super();
    }

    @Override
    public LanguageLevel findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<LanguageLevel> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByItem(LanguageLevel object) throws SQLException {
        super.deleteByItem(object);
    }
}