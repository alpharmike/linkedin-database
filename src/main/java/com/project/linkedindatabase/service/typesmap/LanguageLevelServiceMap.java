package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.repository.types.LanguageLevelRepository;
import com.project.linkedindatabase.service.types.LanguageLevelService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageLevelServiceMap  implements LanguageLevelService {

    private final LanguageLevelRepository languageLevelRepository;

    public LanguageLevelServiceMap() throws SQLException {
        languageLevelRepository = new LanguageLevelRepository();
    }

    @Override
    public LanguageLevel findById(Long id) throws SQLException {
        return languageLevelRepository.findById(id);
    }

    @Override
    public void save(LanguageLevel object) throws SQLException {
        languageLevelRepository.save(object);
    }

    @Override
    public List<LanguageLevel> findAll() throws SQLException {
        return languageLevelRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        languageLevelRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        languageLevelRepository.createTable();
    }

    @Override
    public void deleteByObject(LanguageLevel object) throws SQLException {
        languageLevelRepository.deleteByObject(object);
    }
}