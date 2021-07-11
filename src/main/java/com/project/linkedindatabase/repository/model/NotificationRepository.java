package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationRepository extends BaseRepository<Notification,Long>  {

    public NotificationRepository() throws SQLException {
        super(Notification.class);
    }



    @Override
    public void save(Notification object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileId, notificationType, targetProfileId, text) VALUES (?, ?, ?, ?)");
        ps.setLong(1, object.getProfileId());
        ps.setLong(2, object.getNotificationType());
        ps.setLong(3, object.getTargetProfileId());
        ps.setString(4, object.getText());
        ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileId bigint not null," +
                "notificationType bigint not null," +
                "targetProfileId bigint not null," +
                "text TEXT not null," +
                "foreign key (profileId) references profile(id)," +
                "foreign key (notificationType) references notification_type(id)," +
                "foreign key (targetProfileId) references profile(id)" +
            ")"
        );

        ps.execute();
    }



    @Override
    public Notification convertSql(ResultSet resultSet) {
        Notification notification = new Notification();
        try {
            resultSet.first();
            notification.setId(resultSet.getLong("id"));
            notification.setProfileId(resultSet.getLong("profileId"));
            notification.setNotificationType(resultSet.getLong("notificationType"));
            notification.setTargetProfileId(resultSet.getLong("targetProfileId"));
            notification.setText(resultSet.getString("text"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return notification;
    }
}
