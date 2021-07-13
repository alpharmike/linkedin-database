package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationKnowledgeRepository extends BaseTypeRepository<RelationKnowledge> {
    public RelationKnowledgeRepository() throws SQLException {
        super(RelationKnowledge.class);
    }



    @Override
    public RelationKnowledge convertSql(ResultSet resultSet) throws SQLException {
        RelationKnowledge type = new RelationKnowledge();

        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        type = new RelationKnowledge();
        type.setId(id);
        type.setName(name);

        return type;
    }


}
