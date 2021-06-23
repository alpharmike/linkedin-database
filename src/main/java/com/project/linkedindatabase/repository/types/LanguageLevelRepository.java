package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.LanguageLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.LanguageLevelService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageLevelRepository extends BaseTypeRepository<LanguageLevel> {
    public LanguageLevelRepository() throws SQLException {
        super(LanguageLevel.class);
    }


    @Override
    public LanguageLevel convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new LanguageLevel();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
