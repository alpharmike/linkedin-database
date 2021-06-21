package com.project.linkedindatabase.service.typesmap;

import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.repository.types.NotificationTypeRepository;
import com.project.linkedindatabase.service.types.NotificationTypeService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationTypeServiceMap extends NotificationTypeRepository implements NotificationTypeService {
    public NotificationTypeServiceMap() throws SQLException {
        super();
    }

    @Override
    public NotificationType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<NotificationType> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void deleteByObject(NotificationType object) throws SQLException {
        super.deleteByObject(object);
    }
}
