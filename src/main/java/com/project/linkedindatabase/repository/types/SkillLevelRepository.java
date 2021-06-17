package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class SkillLevelRepository extends BaseTypeRepository {
    public SkillLevelRepository() throws SQLException {
        super(SkillLevel.class);
    }
}
