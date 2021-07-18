package com.project.linkedindatabase.jsonToPojo;


import com.project.linkedindatabase.domain.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationJson {
    private Long id;
    private Long profileId;
    private ProfileJson profileJson;

    private Long notificationType;// foreign key to notificationType ==>enum
    private String notificationTypeName;// foreign key to notificationType ==>enum

    private Long targetProfileId;//notification will send for him
    private ProfileJson targetProfileJson;

    private String body;

    public static NotificationJson convertToJson(Notification notification)
    {
        NotificationJson notificationJson = new NotificationJson();
        notificationJson.setNotificationType(notification.getNotificationType());
        notificationJson.setId(notification.getId());
        notificationJson.setProfileId(notification.getProfileId());
        notificationJson.setTargetProfileId(notification.getTargetProfileId());
        notificationJson.setBody(notification.getBody());
        return  notificationJson;

    }


    public  Notification convertToNotification()
    {
        Notification notification = new Notification();
        notification.setNotificationType(getNotificationType());
        notification.setId(getId());
        notification.setProfileId(getProfileId());
        notification.setTargetProfileId(getTargetProfileId());
        notification.setBody(getBody());
        return  notification;

    }
}
