package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.model.ProfileRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProfileServiceMap implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceMap() throws SQLException {
        profileRepository = new ProfileRepository();
    }

    @Override
    public Profile findById(Long aLong) throws SQLException {
        return profileRepository.findById(aLong);
    }

    @Override
    public void save(Profile object) throws SQLException {
        profileRepository.save(object);
    }

    @Override
    public List<Profile> findAll() throws SQLException {
        return profileRepository.findAll();
    }

    @Override
    public void deleteByObject(Profile object) throws SQLException {
        profileRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        profileRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        profileRepository.createTable();
    }
}