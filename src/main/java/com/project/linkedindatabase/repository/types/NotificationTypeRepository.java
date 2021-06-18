package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.NotificationTypeService;

import java.sql.SQLException;

public class NotificationTypeRepository extends BaseTypeRepository<NotificationType> {
    public NotificationTypeRepository() throws SQLException {
        super(NotificationType.class);
    }
}
