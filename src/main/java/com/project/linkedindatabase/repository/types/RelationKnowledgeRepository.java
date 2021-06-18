package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;

import java.sql.SQLException;

public class RelationKnowledgeRepository extends BaseTypeRepository<RelationKnowledge> {
    public RelationKnowledgeRepository() throws SQLException {
        super(RelationKnowledge.class);
    }
}
