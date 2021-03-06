package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.jsonToPojo.NotificationJson;
import com.project.linkedindatabase.repository.model.NotificationRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import com.project.linkedindatabase.service.types.NotificationTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceMap  implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ProfileService profileService;
    private final ConnectService connectService;
    private final SkillService skillService;

    public NotificationServiceMap(ProfileService profileService, ConnectService connectService, SkillService skillService,
                                  NotificationTypeService notificationTypeService) throws SQLException {
        this.profileService = profileService;
        this.connectService = connectService;
        this.skillService = skillService;
        this.notificationRepository = new NotificationRepository(this.profileService, this.connectService, this.skillService, notificationTypeService);
    }

    @Override
    public Notification findById(Long aLong) throws SQLException {
        return notificationRepository.findById(aLong);
    }

    @Override
    public void save(Notification object) throws SQLException {
        notificationRepository.save(object);
    }

    @Override
    public List<Notification> findAll() throws SQLException {
        return notificationRepository.findAll();
    }

    @Override
    public void deleteByObject(Notification object) throws SQLException {
        notificationRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        notificationRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        notificationRepository.createTable();
    }

    @Override
    public void saveProfileVisitNotification(long profileId, long targetProfileId) throws Exception {
        notificationRepository.saveProfileVisitNotification(profileId, targetProfileId);
    }

    @Override
    public void saveEndorsementNotification(long profileId, long targetProfileId) throws Exception {
        notificationRepository.saveEndorsementNotification(profileId, targetProfileId);
    }

    @Override
    public void saveBirthdayNotification(long profileId) throws Exception {
        notificationRepository.saveBirthdayNotification(profileId);
    }

    @Override
    public void saveChangedWorkExperienceNotification(long profileId) throws Exception {
        notificationRepository.saveChangedWorkExperienceNotification(profileId);
    }

    @Override
    public ArrayList<NotificationJson> getAllNotificationsByTargetProfileId(long targetProfileId) throws SQLException {
        return notificationRepository.getAllNotificationsByTargetProfileId(targetProfileId);
    }

    @Override
    public void saveLikePostNotification(long profileId, long targetProfileId) throws Exception {
        notificationRepository.saveLikePostNotification(profileId,targetProfileId);
    }

    @Override
    public void saveCommentPostNotification(long profileId, long targetProfileId) throws Exception {
        notificationRepository.saveCommentPostNotification(profileId,targetProfileId);

    }

    @Override
    public void saveLikeOrReCommentNotification(long profileId, long targetProfileId, String message) throws Exception {
        notificationRepository.saveLikeOrReCommentNotification(profileId,targetProfileId,message);

    }
    @Override
    public void createBirthDayNotification() throws SQLException{
        notificationRepository.createBirthDayNotification();
    }

}
