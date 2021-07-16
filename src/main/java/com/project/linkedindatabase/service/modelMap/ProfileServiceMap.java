package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.repository.model.ProfileRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProfileServiceMap implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceMap(ProfileRepository profileRepository) throws SQLException {
        this.profileRepository = profileRepository;
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

    public Profile findByUsername(String username) throws SQLException {
        return profileRepository.findByUsername(username);
    }

    @Override
    public List<Profile> searchOtherBaseCurrentCompany(String companyName) throws Exception {
        return profileRepository.searchOtherBaseCurrentCompany(companyName);
    }

    @Override
    public void update(Profile profile) throws Exception {
        profileRepository.update( profile);
    }

    @Override
    public Profile convertSql(ResultSet resultSet) throws Exception {
        return profileRepository.convertSql(resultSet);
    }

    @Override
    public List<Profile> searchOtherBaseName(String name) throws Exception {
        return profileRepository.searchOtherBaseName(name);
    }

    @Override
    public List<Profile> searchOtherBaseLanguage( String language) throws SQLException {
        return profileRepository.searchOtherBaseLanguage( language) ;
    }

    @Override
    public List<Profile> searchOtherBaseLocation(String location) throws SQLException {
        return profileRepository.searchOtherBaseLocation(location);
    }

    @Override
    public ProfileJson getProfileByIdJson(Long id) throws Exception {
        return profileRepository.getProfileByIdJson(id);
    }

    @Override
    public void setCurrentEducation(Profile profile) throws Exception {
        profileRepository.setCurrentEducation(profile);
    }

    @Override
    public void removeCurrentPosition(Profile profile) throws Exception {
        profileRepository.removeCurrentPosition(profile);
    }

    @Override
    public void removeCurrentEducation(Profile profile) throws Exception {
        profileRepository.removeCurrentEducation(profile);
    }

    @Override
    public void setCurrentPosition(Profile profile) throws Exception {
        profileRepository.setCurrentPosition(profile);
    }


    @Override
    public boolean uniqueUsernameEmailPhone(String username, String email, String phone) throws SQLException{
        return this.profileRepository.uniqueUsernameEmailPhone(username,email,phone);
    }
}
