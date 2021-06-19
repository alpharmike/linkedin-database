package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProfileRepository extends BaseRepository<Profile,Long>  implements ProfileService {

    public ProfileRepository() throws SQLException {
        super(Profile.class);
    }

    @Override
    public Profile findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Profile> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Profile object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Profile object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Profile convertSql(ResultSet resultSet) {
        return null;
    }
}
