package com.project.linkedindatabase.repository.model.skill;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.RelationKnowledge;
import com.project.linkedindatabase.domain.Type.SkillLevel;
import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.jsonToPojo.EndorsementPoJo;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.types.RelationKnowledgeService;
import com.project.linkedindatabase.service.types.SkillLevelService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EndorsementRepository extends BaseRepository<Endorsement,Long>  {

    private final SkillLevelService skillLevelService;
    private final RelationKnowledgeService relationKnowledgeService;
    private final ProfileService profileService;

    public EndorsementRepository(SkillLevelService skillLevelService, RelationKnowledgeService relationKnowledgeService, ProfileService profileService) throws SQLException {
        super(Endorsement.class);
        this.skillLevelService = skillLevelService;
        this.relationKnowledgeService = relationKnowledgeService;
        this.profileService = profileService;
    }


    @Override
    public void save(Endorsement object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO "+this.tableName+"(skillId, skillLevel," +
                " relationKnowledge, endorserId) VALUES(?, ?, ?, ?)");
        savePs.setLong(1, object.getSkillId());
        savePs.setLong(2, object.getSkillLevel());
        savePs.setLong(3, object.getRelationKnowledge());
        savePs.setLong(4, object.getEndorserId());
        savePs.execute();
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



    public ArrayList<Endorsement> convertAllSql(ResultSet resultSet) throws SQLException{
        ArrayList<Endorsement> result = new ArrayList<>();
        while (resultSet.next()){
            Endorsement endorsement = new Endorsement();
            endorsement.setId(resultSet.getLong("id"));
            endorsement.setSkillId(resultSet.getLong("skillId"));
            endorsement.setSkillLevel(resultSet.getLong("skillLevel"));
            endorsement.setRelationKnowledge(resultSet.getLong("relationKnowledge"));
            endorsement.setEndorserId(resultSet.getLong("endorserId"));
            result.add(endorsement);
        }
        return result;
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

    public void deleteById(long id) throws SQLException {
        PreparedStatement deletePs = this.conn.prepareStatement("DELETE FROM "+this.tableName+" WHERE id=?");
        deletePs.setLong(1, id);
        deletePs.execute();
    }


    public List<Endorsement> getAllById(long id) throws SQLException {
        PreparedStatement retrieveAllPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE skillId=?");
        retrieveAllPs.setLong(1, id);
        ResultSet resultSet = retrieveAllPs.executeQuery();
        return this.convertAllSql(resultSet);
    }

    public List<Endorsement> getAllByProfileId(long profileId) throws SQLException {
        PreparedStatement retrieveAllPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE skillId IN" +
                "(SELECT id FROM "+BaseEntity.getTableName(Skill.class)+" WHERE profileId=?)");
        retrieveAllPs.setLong(1, profileId);
        ResultSet resultSet = retrieveAllPs.executeQuery();
        return this.convertAllSql(resultSet);
    }

    public List<Endorsement> getAllBySkillId(long skillId) throws SQLException {
        PreparedStatement retrieveAllPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE skillId IN" +
                "(SELECT id FROM "+BaseEntity.getTableName(Skill.class)+" WHERE skillId=?)");
        retrieveAllPs.setLong(1, skillId);
        ResultSet resultSet = retrieveAllPs.executeQuery();
        return this.convertAllSql(resultSet);
    }

    public List<EndorsementPoJo> getAllBySkillIdJson(long skillId) throws SQLException {
        var endorsements = getAllBySkillId(skillId);

        var result = new ArrayList<EndorsementPoJo>();

        List<RelationKnowledge> relationKnowledges = relationKnowledgeService.findAll();
        List<SkillLevel> skillLevels = skillLevelService.findAll();

        for (Endorsement endorsement: endorsements) {
            var item = EndorsementPoJo.convertToJson(endorsement);
            for (SkillLevel i : skillLevels)
            {
                if (i.getId() == item.getSkillLevel())
                {
                    item.setSkillLevelName(i.getName());
                    break;
                }
            }

            for (RelationKnowledge i : relationKnowledges)
            {
                if (i.getId() == item.getRelationKnowledge())
                {
                    item.setSkillLevelName(i.getName());
                    break;
                }
            }

            Profile profile = profileService.findById(item.getEndorserId());
            item.setProfileJson(ProfileJson.convertToJson(profile));
            result.add(item);
        }

        return result;

    }

    public void updateWithProfileId(Endorsement endorsement) throws SQLException {
        PreparedStatement updatePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET skillId = ?," +
                " skillLevel = ?, relationKnowledge = ?  WHERE id = ? and endorserId = ?");
        updatePs.setLong(1, endorsement.getSkillId());
        updatePs.setLong(2, endorsement.getSkillLevel());
        updatePs.setLong(3, endorsement.getRelationKnowledge());
        updatePs.setLong(4, endorsement.getId());
        updatePs.setLong(5, endorsement.getEndorserId());
        updatePs.execute();
    }

    public boolean isThereAnotherEndorsement(Endorsement endorsement) throws SQLException {



        PreparedStatement ps = conn.prepareStatement("select *  from "+this.getTableName()+" where skillId = ? and endorserId = ?");
        ps.setLong(1, endorsement.getSkillId());
        ps.setLong(2, endorsement.getEndorserId());
        ResultSet resultSet = ps.executeQuery();
        if (!resultSet.isBeforeFirst() ) {
            return false;
        }else
        {
            return true;
        }
        // return resultSet.isBeforeFirst();

    }



}

