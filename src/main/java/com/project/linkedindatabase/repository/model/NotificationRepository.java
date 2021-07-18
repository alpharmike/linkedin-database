package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.jsonToPojo.NotificationJson;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import com.project.linkedindatabase.service.types.NotificationTypeService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationRepository extends BaseRepository<Notification,Long>  {
    private final ProfileService profileService;
    private final ConnectService connectService;
    private final SkillService skillService;
    private final NotificationTypeService notificationTypeService;

    public NotificationRepository(ProfileService profileService, ConnectService connectService, SkillService skillService,
                                  NotificationTypeService notificationTypeService) throws SQLException {
        super(Notification.class);
        this.profileService = profileService;
        this.connectService = connectService;
        this.skillService = skillService;
        this.notificationTypeService = notificationTypeService;
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
        notification.setNotificationType(notificationTypeService.findByName("profile visit").getId());
        notification.setTargetProfileId(targetProfileId);
        String username1 = profileService.getProfileByIdJson(profileId).getUsername();
        notification.setBody(username1+" just visited your profile.");
        this.save(notification);
    }

    public void saveEndorsementNotification(long profileId, long skillId) throws Exception {
        Notification notification = new Notification();
        notification.setProfileId(profileId);
        notification.setNotificationType(notificationTypeService.findByName("skill endorse").getId());
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
            notification.setNotificationType(notificationTypeService.findByName("birthday").getId());
            notification.setTargetProfileId(c.getProfileIdRequest());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody("It is "+username+ " 's birthday.");
            this.save(notification);
        }
        for (Connect c:requestedConnects // send birthday notification to all who <profileId> sent requests which are accepted
             ) {
            Notification notification = new Notification();
            notification.setProfileId(profileId);
            notification.setNotificationType(notificationTypeService.findByName("birthday").getId());
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
            notification.setNotificationType(notificationTypeService.findByName("change job").getId());
            notification.setTargetProfileId(c.getProfileIdRequest());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody(username +" has changed his working station or his job check it out.");
            this.save(notification);
        }
        for (Connect c:requestedConnects
        ) {
            Notification notification = new Notification();
            notification.setProfileId(profileId);
            notification.setNotificationType(notificationTypeService.findByName("change job").getId());
            notification.setTargetProfileId(c.getProfileIdReceive());
            String username = profileService.getProfileByIdJson(profileId).getUsername();
            notification.setBody(username +" has changed his working station or his job check it out.");
            this.save(notification);
        }
    }

    public ArrayList<NotificationJson> getAllNotificationsByTargetProfileId(long targetProfileId) throws SQLException {
        PreparedStatement retrieveAllPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName
                +" WHERE targetProfileId = ?");
        retrieveAllPs.setLong(1, targetProfileId);
        ResultSet resultSet = retrieveAllPs.executeQuery();
        List<Notification> notifications = this.convertAllSql(resultSet);
        ArrayList<NotificationJson> notificationJsons = new ArrayList<>();
        for (Notification i: notifications)
        {
            notificationJsons.add(convertToJson(i));
        }
        return notificationJsons;
    }

    public void saveLikePostNotification(long profileId, long targetProfileId) throws Exception {
        Notification notification = new Notification();
        notification.setProfileId(profileId);
        notification.setNotificationType(notificationTypeService.findByName("like for post").getId());
        notification.setTargetProfileId(targetProfileId);
        String username1 = profileService.getProfileByIdJson(profileId).getUsername();
        notification.setBody(username1+" liked your post.");
        this.save(notification);
    }

    public void saveCommentPostNotification(long profileId, long targetProfileId) throws Exception {
        Notification notification = new Notification();
        notification.setProfileId(profileId);
        notification.setNotificationType(notificationTypeService.findByName("comment for post").getId());
        notification.setTargetProfileId(targetProfileId);
        String username1 = profileService.getProfileByIdJson(profileId).getUsername();
        notification.setBody(username1+" commented on your post.");
        this.save(notification);
    }

    public void saveLikeOrReCommentNotification(long profileId, long targetProfileId,String message) throws Exception {
        Notification notification = new Notification();
        notification.setProfileId(profileId);
        notification.setNotificationType(notificationTypeService.findByName("like or replay for comment").getId());
        notification.setTargetProfileId(targetProfileId);
        String username1 = profileService.getProfileByIdJson(profileId).getUsername();
        notification.setBody(username1+" "+message);
        this.save(notification);
    }

    public NotificationJson convertToJson(Notification notification) throws SQLException
    {
        NotificationJson notificationJson = NotificationJson.convertToJson(notification);
        Profile profile = profileService.findById(notificationJson.getProfileId());
        Profile targetProfile = profileService.findById(notificationJson.getTargetProfileId());
        notificationJson.setProfileJson(ProfileJson.convertToJson(profile));
        notificationJson.setTargetProfileJson(ProfileJson.convertToJson(targetProfile));
        NotificationType notificationType = notificationTypeService.findById(notification.getNotificationType());
        notificationJson.setNotificationTypeName(notificationType.getName());
        return notificationJson;
    }

}
