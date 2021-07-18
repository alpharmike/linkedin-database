package com.project.linkedindatabase.service.model;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.jsonToPojo.NotificationJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotificationService extends BaseService<Notification,Long> {
    public void saveProfileVisitNotification(long profileId, long targetProfileId) throws Exception;
    public void saveEndorsementNotification(long profileId, long targetProfileId) throws Exception;
    public void saveBirthdayNotification(long profileId) throws Exception;
    public void saveChangedWorkExperienceNotification(long profileId) throws Exception;
    public ArrayList<NotificationJson> getAllNotificationsByTargetProfileId(long targetProfileId) throws SQLException;
    public void saveLikePostNotification(long profileId, long targetProfileId) throws Exception ;

    public void saveCommentPostNotification(long profileId, long targetProfileId) throws Exception;

    public void saveLikeOrReCommentNotification(long profileId, long targetProfileId,String message) throws Exception ;
    public void createBirthDayNotification() throws SQLException;
}
