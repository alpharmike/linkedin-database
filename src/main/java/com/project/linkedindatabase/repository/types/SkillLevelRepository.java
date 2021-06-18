package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.SkillLevelService;

import java.sql.SQLException;

public class SkillLevelRepository extends BaseTypeRepository<SkillLevel> implements SkillLevelService {
    public SkillLevelRepository() throws SQLException {
        super(SkillLevel.class);
    }
}
