package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.LanguageLevelService;

import java.sql.SQLException;

public class LanguageLevelRepository extends BaseTypeRepository<LanguageLevel> implements LanguageLevelService {
    public LanguageLevelRepository() throws SQLException {
        super(LanguageLevel.class);
    }
}
