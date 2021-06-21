package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.repository.types.RelationKnowledgeRepository;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RelationKnowledgeServiceMap extends RelationKnowledgeRepository implements RelationKnowledgeService {
    public RelationKnowledgeServiceMap() throws SQLException {
        super();
    }

    @Override
    public RelationKnowledge findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<RelationKnowledge> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByObject(RelationKnowledge object) throws SQLException {
        super.deleteByObject(object);
    }
}