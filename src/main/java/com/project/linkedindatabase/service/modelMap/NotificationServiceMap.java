package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.repository.model.NotificationRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationServiceMap  implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceMap() throws SQLException {
        this.notificationRepository = new NotificationRepository();
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
}
