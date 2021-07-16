package com.project.linkedindatabase.service.modelMap.accomplishment;

import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.jsonToPojo.LanguageJson;
import com.project.linkedindatabase.repository.model.accomplishment.LanguageRepository;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import com.project.linkedindatabase.service.types.LanguageLevelService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LanguageServiceMap implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceMap(LanguageLevelService languageLevelService) throws SQLException {
        this.languageRepository = new LanguageRepository(languageLevelService);
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

    @Override
    public List<Language> findByProfileId(Long id) throws Exception {
        return languageRepository.findByProfileId(id);
    }

    @Override
    public void update(Language language) throws Exception {
        languageRepository.update(language);
    }

    @Override
    public void updateWithProfileId(Language language) throws Exception {
        languageRepository.updateWithProfileId(language);
    }

    @Override
    public void deleteByIdAndProfileId(Language language) throws Exception {
        languageRepository.deleteByIdAndProfileId(language);
    }

    @Override
    public List<LanguageJson> getAllByProfileIdJson(Long profileId) throws SQLException
    {
        return languageRepository.getAllByProfileIdJson(profileId);
    }
}
