package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.SkillLevelService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SkillLevelRepository extends BaseTypeRepository<SkillLevel> {
    public SkillLevelRepository() throws SQLException {
        super(SkillLevel.class);
    }




    @Override
    public SkillLevel convertSql(ResultSet resultSet) throws SQLException {
        SkillLevel skillLevel = new SkillLevel();
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new SkillLevel();
        skillLevel.setId(id);
        skillLevel.setName(name);

        return skillLevel;
    }


}
