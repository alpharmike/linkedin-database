package com.project.linkedindatabase.repository.model.skill;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EndorsementRepository extends BaseRepository<Endorsement,Long>  {

    public EndorsementRepository() throws SQLException {
        super(Endorsement.class);
    }


    @Override
    public void save(Endorsement object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO "+this.tableName+"(skillId, skillLevel," +
                " relationKnowledge, endorserId) VALUES(?, ?, ?, ?)");
        savePs.setLong(1, object.getSkillId());
        savePs.setLong(2, object.getSkillLevel());
        savePs.setLong(3, object.getRelationKnowledge());
        savePs.setLong(4, object.getEndorserId());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "skillId BIGINT NOT NULL," +
                "FOREIGN KEY (skillId) REFERENCES " + BaseEntity.getTableName(Skill.class) + "(id)," +
                "skillLevel BIGINT NOT NULL," +
                "FOREIGN KEY (skillLevel) REFERENCES " + BaseEntity.getTableName(SkillLevel.class) + "(id),"+
                "relationKnowledge BIGINT NOT NULL," +
                "FOREIGN KEY (relationKnowledge) REFERENCES " +  BaseEntity.getTableName(RelationKnowledge.class) + "(id),"+
                "endorserId BIGINT NOT NULL," +
                "FOREIGN KEY (endorserId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "PRIMARY KEY (id)"+
            ")"
        );

        createTablePs.execute();
    }


    @Override
    public Endorsement convertSql(ResultSet resultSet) throws SQLException {
        Endorsement endorsement = new Endorsement();
        try{
            resultSet.first();
            endorsement.setId(resultSet.getLong("id"));
            endorsement.setSkillId(resultSet.getLong("skillId"));
            endorsement.setSkillLevel(resultSet.getLong("skillLevel"));
            endorsement.setRelationKnowledge(resultSet.getLong("relationKnowledge"));
            endorsement.setEndorserId(resultSet.getLong("endorserId"));
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return endorsement;
    }

    public Endorsement getById(Long id) throws SQLException {

        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName +" WHERE id=?",ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        retrievePs.setLong(1, id);
        ResultSet resultSet = retrievePs.executeQuery();
        return this.convertSql(resultSet);
    }

    public Endorsement editById(Long id, Long skillId, Long skillLevel, Long relationKnowledge, Long endorserId) throws SQLException {

        PreparedStatement updatePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET skillId=?," +
                " skillLevel=?, relationKnowledge=?, endorserId=? WHERE id=?");
        updatePs.setLong(1, skillId);
        updatePs.setLong(2, skillLevel);
        updatePs.setLong(3, relationKnowledge);
        updatePs.setLong(4, endorserId);
        updatePs.setLong(5, id);
        updatePs.executeUpdate();
        return this.getById(id);
    }

    public void deleteById(Long id) throws SQLException {
        PreparedStatement deletePs = this.conn.prepareStatement("DELETE FROM "+this.tableName+" WHERE id=?");
        deletePs.setLong(1, id);
        deletePs.execute();
    }
}

