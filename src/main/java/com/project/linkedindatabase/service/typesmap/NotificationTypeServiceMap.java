package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.repository.types.NotificationTypeRepository;
import com.project.linkedindatabase.service.types.NotificationTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationTypeServiceMap  implements NotificationTypeService {

    private final NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeServiceMap() throws SQLException {
        notificationTypeRepository = new NotificationTypeRepository();
    }

    @Override
    public NotificationType findById(Long id) throws SQLException {
        return notificationTypeRepository.findById(id);
    }

    @Override
    public void save(NotificationType object) throws SQLException {
        notificationTypeRepository.save(object);
    }

    @Override
    public List<NotificationType> findAll() throws SQLException {
        return notificationTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        notificationTypeRepository.deleteById(id);
    }

    @Override
    public void createTable() throws SQLException {
        notificationTypeRepository.createTable();
    }

    @Override
    public void deleteByObject(NotificationType object) throws SQLException {
        notificationTypeRepository.deleteByObject(object);
    }
}
