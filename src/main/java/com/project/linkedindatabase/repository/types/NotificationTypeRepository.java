package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.NotificationTypeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationTypeRepository extends BaseTypeRepository<NotificationType> {
    public NotificationTypeRepository() throws SQLException {
        super(NotificationType.class);
    }


    @Override
    public NotificationType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new NotificationType();
        type.setId(id);
        type.setName(name);

        return type;
    }

}
