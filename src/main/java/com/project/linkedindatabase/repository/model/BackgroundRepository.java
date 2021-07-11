package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.BaseService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.NotificationService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundRepository extends BaseRepository<Background,Long>  {

    public BackgroundRepository() throws SQLException {
        super(Background.class);
    }



    @Override
    public void save(Background object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileId, backgroundType, startDate, endDate, title, description) VALUES (?, ?, ?, ?, ?, ?)");
        ps.setLong(1, object.getProfileId());
        ps.setLong(2, object.getBackgroundType());
        ps.setDate(3, object.getStartDate());
        ps.setDate(4, object.getEndDate());
        ps.setString(5, object.getTitle());
        ps.setString(6, object.getDescription());
        ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileId bigint not null," +
                "backgroundType bigint not null," +
                "startDate date not null," +
                "endDate date not null," +
                "title nvarchar(100) not null," +
                "description TEXT not null," +
                "foreign key (profileId) references profile(id)" +
                "foreign key (backgroundType) references background_type(id)"
        );

        ps.execute();
    }



    @Override
    public Background convertSql(ResultSet resultSet) {
        Background background = new Background();
        try{
            resultSet.first();
            background.setId(resultSet.getLong("id"));
            background.setProfileId(resultSet.getLong("profileId"));
            background.setBackgroundType(resultSet.getLong("backgroundType"));
            background.setStartDate(resultSet.getDate("startDate"));
            background.setEndDate(resultSet.getDate("endDate"));
            background.setTitle(resultSet.getString("title"));
            background.setDescription(resultSet.getString("description"));
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return background;
    }
}
