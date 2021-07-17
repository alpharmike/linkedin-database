package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class NotificationRepository extends BaseRepository<Notification,Long>  {
    private final ProfileService profileService;
    private final ConnectService connectService;
    private final SkillService skillService;

    public NotificationRepository(ProfileService profileService, ConnectService connectService, SkillService skillService) throws SQLException {
        super(Notification.class);
        this.profileService = profileService;
        this.connectService = connectService;
        this.skillService = skillService;
    }



    @Override
    public void save(Notification object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileId, notificationType, targetProfileId, body) VALUES (?, ?, ?, ?)");
        ps.setLong(1, object.getProfileId());
        ps.setLong(2, object.getNotificationType());
        ps.setLong(3, object.getTargetProfileId());
        ps.setString(4, object.getBody());
        ps.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileId bigint not null," +
                "notificationType bigint not null," +
                "targetProfileId bigint not null," +
                "body TEXT not null," +
                "foreign key (profileId) references " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "foreign key (notificationType) references " +  BaseEntity.getTableName(NotificationType.class) + "(id),"+
                "foreign key (targetProfileId) references " +  BaseEntity.getTableName(Profile.class) + "(id)"+
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
            notification.setBody(resultSet.getString("body"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return notification;
    }

    public ArrayList<Notification> convertAllSql(ResultSet resultSet) throws SQLException {
        ArrayList<Notification> result = new ArrayList<>();
        while (resultSet.next()){
            Notification notification = new Notification();
            notification.setId(resultSet.getLong("id"));
            notification.setProfileId(resultSet.getLong("profileId"));
            notification.setNotificationType(resultSet.getLong("notificationType"));
            notification.setTargetProfileId(resultSet.getLong("targetProfileId"));
            notification.setBody(resultSet.getString("body"));
            result.add(notification);
        }
        return  result;
    }



    public void saveProfileVisitNotification(long profileId, long targetProfileId) throws Exception {
        Notification notification = new Notification();
        notification.setProfileId(profileId);
        notification.setNotificationType((long) 3);
        notification.setTargetProfileId(targetProfileId);
        String username1 = profileService.getProfileByIdJson(profileId).getUsername();
        notification.setBody(username1+" just visited your profile.");
        this.save(notification);
    }

    public void saveEndorsementNotification(long profileId, long skillId) throws Exception {
        Notification notification = new Notification();
        notification.setProfileId(profileId);
        notification.setNotificationType((long) 7);
        Skill skill = skillService.getById(skillId);
        notification.setTargetProfileId(skill.getProfileId());
        String username1 = profileService.getProfileByIdJson(profileId).getUsername();
        notification.setBody(username1+" just endorsed "+ skill.getName());
        this.save(notification);
    }

    public void saveBirthdayNotification(long profileId) throws Exception {
        ArrayList<Connect> receivedConnects, requestedConnects = new ArrayList<>();
        receivedConnects = (ArrayList<Connect>) connectService.getReceiverRequestsBaseOnType(profileId, "accept");
        requestedConnects = (ArrayList<Connect>) connectService.getSenderRequestsBaseOnType(profileId, "accept");
        for (Connect c:receivedConnects // send birthday notification to all who sent requests to <profileId> which their requests are accepted (received connects)
             ) {
            Notification notification = new Notification();
            notification.setProfileId(profileId);
            notification.setNotificationType((long) 9);
            notification.setTargetProfileId(c.getProfileIdRequest());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody("It is "+username+ " 's birthday.");
            this.save(notification);
        }
        for (Connect c:requestedConnects // send birthday notification to all who <profileId> sent requests which are accepted
             ) {
            Notification notification = new Notification();
            notification.setProfileId(profileId);
            notification.setNotificationType((long) 9);
            notification.setTargetProfileId(c.getProfileIdReceive());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody("It is "+username+ " 's birthday.");
            this.save(notification);
        }
    }

    public void saveChangedWorkExperienceNotification(long profileId) throws Exception {
        ArrayList<Connect> receivedConnects, requestedConnects = new ArrayList<>();
        receivedConnects = (ArrayList<Connect>) connectService.getReceiverRequestsBaseOnType(profileId, "accept");
        requestedConnects = (ArrayList<Connect>) connectService.getSenderRequestsBaseOnType(profileId, "accept");
        for (Connect c:receivedConnects
        ) {
            Notification notification = new Notification();
            notification.setProfileId(profileId);
            notification.setNotificationType((long) 9);
            notification.setTargetProfileId(c.getProfileIdRequest());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody(username +" has changed his working station or his job check it out.");
            this.save(notification);
        }
        for (Connect c:requestedConnects
        ) {
            Notification notification = new Notification();
            notification.setProfileId(profileId);
            notification.setNotificationType((long) 9);
            notification.setTargetProfileId(c.getProfileIdReceive());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody(username +" has changed his working station or his job check it out.");
            this.save(notification);
        }
    }

    public ArrayList<Notification> getAllNotificationsByTargetProfileId(long targetProfileId) throws SQLException {
        PreparedStatement retrieveAllPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName
                +" WHERE targetProfileId=?");
        retrieveAllPs.setLong(1, targetProfileId);
        ResultSet resultSet = retrieveAllPs.executeQuery();
        return this.convertAllSql(resultSet);
    }

}
