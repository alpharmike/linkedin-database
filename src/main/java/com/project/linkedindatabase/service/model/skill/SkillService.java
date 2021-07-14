package com.project.linkedindatabase.service.model.skill;

import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;

public interface SkillService extends BaseService<Skill,Long> {
    public void update(Skill skill) throws SQLException;
}
