package com.project.linkedindatabase.repository.model.skill;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.skill.Endorsement;
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
        savePs.setString(0, this.tableName);
        savePs.setLong(1, object.getSkillId());
        savePs.setLong(2, object.getSkillLevel());
        savePs.setLong(3, object.getRelationKnowledge());
        savePs.setLong(4, object.getEndorserId());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+this.tableName+"(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "skillId BIGINT," +
                "FOREIGN KEY (skillId) REFERENCES skill(id),"+
                "skillLevel BIGINT," +
                "FOREIGN KEY (skillLevel) REFERENCES skill_level_type(id),"+
                "relationKnowledge BIGINT," +
                "FOREIGN KEY (relationKnowledge) REFERENCES relation_knowledge_type(id),"+
                "endorserId BIGINT," +
                "FOREIGN KEY (endorserId) REFERENCES profile(id),"+
                "PRIMARY KEY (id)"+
                ")");

        createTablePs.execute();
    }


    @Override
    public Endorsement convertSql(ResultSet resultSet) {
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
}

