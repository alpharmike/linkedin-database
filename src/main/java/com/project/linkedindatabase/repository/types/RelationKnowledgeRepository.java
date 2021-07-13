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
        try{
            resultSet.first();
            String name = resultSet.getString(NAME);
            Long id = resultSet.getLong(ID);
            type = new RelationKnowledge();
            type.setId(id);
            type.setName(name);
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return type;
    }

    public RelationKnowledge getById(long id) throws SQLException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName +" WHERE id=?",ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        retrievePs.setLong(1, id);
        ResultSet resultSet = retrievePs.executeQuery();
        return this.convertSql(resultSet);
    }
}
