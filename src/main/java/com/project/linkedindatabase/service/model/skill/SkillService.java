package com.project.linkedindatabase.service.model.skill;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.jsonToPojo.SkillPoJo;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface SkillService extends BaseService<Skill,Long> {
    public void update(Skill skill) throws SQLException;
    public void  saveMultipleSkill(List<String> skills, Profile profile) throws SQLException;
    public List<SkillPoJo> getAllSkillByProfileJson(Long profileId) throws SQLException;

    public List<Skill> getAllSkillByProfile(Long profileId) throws SQLException;
}
