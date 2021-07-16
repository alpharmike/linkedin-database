package com.project.linkedindatabase.service.modelMap.skill;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.jsonToPojo.SkillPoJo;
import com.project.linkedindatabase.repository.model.skill.SkillRepository;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SkillServiceMap implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceMap(EndorsementService endorsementService) throws SQLException {
        this.skillRepository = new SkillRepository(endorsementService);
    }

    @Override
    public Skill findById(Long aLong) throws SQLException {
        return skillRepository.findById(aLong);
    }

    @Override
    public void save(Skill object) throws SQLException {
        skillRepository.save(object);
    }

    @Override
    public List<Skill> findAll() throws SQLException {
        return skillRepository.findAll();
    }

    @Override
    public void deleteByObject(Skill object) throws SQLException {
        skillRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        skillRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        skillRepository.createTable();
    }

    @Override
    public void update(Skill skill) throws SQLException {
        skillRepository.update(skill);
    }

    @Override
    public void saveMultipleSkill(List<String> skills, Profile profile) throws SQLException {
        skillRepository.saveMultipleSkill(skills,profile);
    }

    @Override
    public List<SkillPoJo> getAllSkillByProfileJson(Long profileId) throws SQLException {
        return skillRepository.getAllSkillByProfileJson(profileId);
    }

    @Override
    public Skill getById(long id) throws SQLException {
        return skillRepository.getById(id);
    }

    @Override
    public List<Skill> getAllSkillByProfile(Long profileId) throws SQLException {
        return skillRepository.getAllSkillByProfile(profileId);
    }
}
