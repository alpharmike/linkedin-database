package com.project.linkedindatabase.service.model.accomplishment;

import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.jsonToPojo.LanguageJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface LanguageService extends BaseService<Language,Long> {
    List<Language> findByProfileId(Long id) throws Exception;

    void update(Language language) throws Exception;

    public void updateWithProfileId(Language language) throws Exception ;

    public void deleteByIdAndProfileId(Language language) throws Exception ;

    List<LanguageJson> getAllByProfileIdJson(Long profileId) throws SQLException;
}
