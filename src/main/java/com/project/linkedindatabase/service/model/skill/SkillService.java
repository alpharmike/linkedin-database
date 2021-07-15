package com.project.linkedindatabase.service.model.skill;

import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface SkillService extends BaseService<Skill,Long> {
    public void update(Skill skill) throws SQLException;
    public List<Skill> findByProfileId(Long id) throws Exception;

}
