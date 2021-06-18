package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.repository.types.SkillLevelRepository;
import com.project.linkedindatabase.service.types.SkillLevelService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SkillLevelServiceMap extends SkillLevelRepository implements SkillLevelService {
    public SkillLevelServiceMap() throws SQLException {
        super();
    }

    @Override
    public SkillLevel findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<SkillLevel> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByItem(SkillLevel object) throws SQLException {
        super.deleteByItem(object);
    }
}
