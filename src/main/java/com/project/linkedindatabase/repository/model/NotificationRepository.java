package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.NotificationService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationRepository extends BaseRepository<Notification,Long> implements NotificationService {

    public NotificationRepository() throws SQLException {
        super(Notification.class);
    }

    @Override
    public Notification findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Notification> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Notification object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Notification object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Notification convertSql(ResultSet resultSet) {
        return null;
    }
}