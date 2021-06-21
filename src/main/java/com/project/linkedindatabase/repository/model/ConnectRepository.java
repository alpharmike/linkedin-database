package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConnectRepository extends BaseRepository<Connect,Long> {

    public ConnectRepository() throws SQLException {
        super(Connect.class);
    }




    @Override
    public void save(Connect object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }



    @Override
    public Connect convertSql(ResultSet resultSet) {
        return null;
    }
}
