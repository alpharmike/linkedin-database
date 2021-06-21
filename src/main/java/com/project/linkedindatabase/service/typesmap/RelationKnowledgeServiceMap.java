package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.repository.types.RelationKnowledgeRepository;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RelationKnowledgeServiceMap  implements RelationKnowledgeService {

    private final RelationKnowledgeRepository relationKnowledgeRepository;

    public RelationKnowledgeServiceMap() throws SQLException {
        relationKnowledgeRepository = new RelationKnowledgeRepository();
    }

    @Override
    public RelationKnowledge findById(Long id) throws SQLException {
        return relationKnowledgeRepository.findById(id);
    }

    @Override
    public void save(RelationKnowledge object) throws SQLException {
        relationKnowledgeRepository.save(object);
    }

    @Override
    public List<RelationKnowledge> findAll() throws SQLException {
        return relationKnowledgeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        relationKnowledgeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        relationKnowledgeRepository.createTable();
    }

    @Override
    public void deleteByObject(RelationKnowledge object) throws SQLException {
        relationKnowledgeRepository.deleteByObject(object);
    }
}