package com.project.linkedindatabase.service.modelMap.skill;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.jsonToPojo.SkillPoJo;
import com.project.linkedindatabase.repository.model.skill.SkillRepository;
import com.project.linkedindatabase.service.model.skill.SkillService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SkillServiceMap implements SkillService {

    private final SkillService skillService;

    public SkillServiceMap(SkillService skillService) throws SQLException {
        this.skillService = skillService;
    }

    @Override
    public Skill findById(Long aLong) throws SQLException {
        return skillService.findById(aLong);
    }

    @Override
    public void save(Skill object) throws SQLException {
        skillService.save(object);
    }

    @Override
    public List<Skill> findAll() throws SQLException {
        return skillService.findAll();
    }

    @Override
    public void deleteByObject(Skill object) throws SQLException {
        skillService.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        skillService.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        skillService.createTable();
    }

    @Override
    public void update(Skill skill) throws SQLException {
        skillService.update(skill);
    }

    @Override
    public void saveMultipleSkill(List<String> skills, Profile profile) throws SQLException {
        skillService.saveMultipleSkill(skills,profile);
    }

    @Override
    public List<SkillPoJo> getAllSkillByProfileJson(Long profileId) throws SQLException {
        return skillService.getAllSkillByProfileJson(profileId);
    }

    @Override
    public List<Skill> getAllSkillByProfile(Long profileId) throws SQLException {
        return skillService.getAllSkillByProfile(profileId);
    }
}
