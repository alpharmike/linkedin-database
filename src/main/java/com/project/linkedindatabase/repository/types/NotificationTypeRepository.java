package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class NotificationTypeRepository extends BaseTypeRepository {
    public NotificationTypeRepository() throws SQLException {
        super(NotificationType.class);
    }
}
