package com.project.linkedindatabase.repository.model.skill;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SkillRepository extends BaseRepository<Skill,Long> implements SkillService {

    public SkillRepository() throws SQLException {
        super(Skill.class);
    }

    @Override
    public Skill findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Skill> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Skill object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Skill object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Skill convertSql(ResultSet resultSet) {
        return null;
    }
}
