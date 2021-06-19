package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.NotificationType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.NotificationTypeService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationTypeRepository extends BaseTypeRepository<NotificationType> implements NotificationTypeService {
    public NotificationTypeRepository() throws SQLException {
        super(NotificationType.class);
    }


    @Override
    public NotificationType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public NotificationType findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public void save(NotificationType object) throws SQLException {
        super.save(object);
    }


    @Override
    public List<NotificationType> findAll() throws SQLException {
        return super.findAll();
    }


    @Override
    public void deleteByObject(NotificationType object) throws SQLException {
        super.deleteByObject(object);
    }


    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }


    @Override
    public void createTable() throws SQLException {
        super.createTable();
    }
}
