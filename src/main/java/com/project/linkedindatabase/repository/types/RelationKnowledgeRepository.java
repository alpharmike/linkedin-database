package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class RelationKnowledgeRepository extends BaseTypeRepository {
    public RelationKnowledgeRepository() throws SQLException {
        super(RelationKnowledge.class);
    }
}
