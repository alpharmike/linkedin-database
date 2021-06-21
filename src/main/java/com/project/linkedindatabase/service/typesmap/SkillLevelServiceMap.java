package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.model.skill.SkillRepository;
import com.project.linkedindatabase.repository.types.SkillLevelRepository;
import com.project.linkedindatabase.service.types.SkillLevelService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SkillLevelServiceMap  implements SkillLevelService {


    private final SkillLevelRepository skillLevelRepository;

    public SkillLevelServiceMap() throws SQLException {
        skillLevelRepository = new SkillLevelRepository();
    }

    @Override
    public SkillLevel findById(Long id) throws SQLException {
        return skillLevelRepository.findById(id);
    }

    @Override
    public void save(SkillLevel object) throws SQLException {
        skillLevelRepository.save(object);
    }

    @Override
    public List<SkillLevel> findAll() throws SQLException {
        return skillLevelRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        skillLevelRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        skillLevelRepository.createTable();
    }

    @Override
    public void deleteByObject(SkillLevel object) throws SQLException {
        skillLevelRepository.deleteByObject(object);
    }
}
