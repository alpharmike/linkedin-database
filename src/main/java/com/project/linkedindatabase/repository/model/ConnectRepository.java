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
public class ConnectRepository extends BaseRepository<Connect,Long> implements ConnectService {

    public ConnectRepository() throws SQLException {
        super(Connect.class);
    }

    @Override
    public Connect findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Connect> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Connect object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Connect object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Connect convertSql(ResultSet resultSet) {
        return null;
    }
}
