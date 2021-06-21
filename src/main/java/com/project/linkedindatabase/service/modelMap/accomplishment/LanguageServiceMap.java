package com.project.linkedindatabase.service.modelMap.accomplishment;

import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.repository.model.accomplishment.LanguageRepository;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageServiceMap implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceMap() throws SQLException {
        this.languageRepository = new LanguageRepository();
    }

    @Override
    public Language findById(Long aLong) throws SQLException {
        return languageRepository.findById(aLong);
    }

    @Override
    public void save(Language object) throws SQLException {
        languageRepository.save(object);
    }

    @Override
    public List<Language> findAll() throws SQLException {
        return languageRepository.findAll();
    }

    @Override
    public void deleteByObject(Language object) throws SQLException {
        languageRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        languageRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        languageRepository.createTable();
    }
}
