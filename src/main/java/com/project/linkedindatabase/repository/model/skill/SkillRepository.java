package com.project.linkedindatabase.repository.model.skill;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SkillRepository extends BaseRepository<Skill,Long>  {

    public SkillRepository() throws SQLException {
        super(Skill.class);
    }



    @Override
    public void save(Skill object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO " + this.tableName + "(name, profileId) VALUES(" +
                "?, ?)");
        savePs.setString(1, object.getName());
        savePs.setLong(2, object.getProfileId());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "name NVARCHAR(255) NOT NULL,"+
                "profileId BIGINT NOT NULL," +
                "FOREIGN KEY (profileId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "PRIMARY KEY (id)"+
            ")"
        );

        createTablePs.execute();
    }

    @Override
    public Skill convertSql(ResultSet resultSet) {
        Skill skill = new Skill();
        try{
            resultSet.first();
            skill.setId(resultSet.getLong("id"));
            skill.setName(resultSet.getString("name"));
            skill.setProfileId(resultSet.getLong("profileId"));
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return skill;
    }

    public Skill getById(long id) throws SQLException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName +" WHERE id=?",ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        retrievePs.setLong(1, id);
        ResultSet resultSet = retrievePs.executeQuery();
        return this.convertSql(resultSet);
    }

    public Skill editById(long id, String name, long profileId) throws SQLException {
        PreparedStatement updatePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET name=?, profileId=? " +
                "WHERE id=?");
        updatePs.setString(1, name);
        updatePs.setLong(2, profileId);
        updatePs.setLong(3, id);
        updatePs.executeUpdate();
        return this.getById(id);
    }

    public void deleteById(long id) throws SQLException {
        PreparedStatement deletePs = this.conn.prepareStatement("DELETE FROM "+this.tableName+" WHERE id=?");
        deletePs.setLong(1, id);
        deletePs.execute();
    }
}
