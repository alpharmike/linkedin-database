package com.project.linkedindatabase.service.modelMap.skill;

import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.repository.model.skill.SkillRepository;
import com.project.linkedindatabase.service.model.skill.SkillService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SkillServiceMap implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceMap() throws SQLException {
        this.skillRepository = new SkillRepository();
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
}
