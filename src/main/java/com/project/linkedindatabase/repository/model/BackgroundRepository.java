package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.BaseService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.NotificationService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BackgroundRepository extends BaseRepository<Background,Long>  {

    public BackgroundRepository() throws SQLException {
        super(Background.class);
    }



    @Override
    public void save(Background object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }



    @Override
    public Background convertSql(ResultSet resultSet) {
        return null;
    }
}
