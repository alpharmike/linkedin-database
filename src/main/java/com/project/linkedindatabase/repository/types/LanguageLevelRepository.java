package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class LanguageLevelRepository extends BaseTypeRepository {
    public LanguageLevelRepository() throws SQLException {
        super(LanguageLevel.class);
    }
}
