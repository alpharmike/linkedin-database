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
    public List<Profile> searchOtherBaseCurrentCompany(Long id,String companyName) throws Exception {
        return profileRepository.searchOtherBaseCurrentCompany(id,companyName);
    }

    @Override
    public void update(Profile profile) throws Exception {
        profileRepository.update( profile);
    }


    @Override
    public boolean uniqueUsernameEmailPhone(String username, String email, String phone) throws SQLException{
        return this.profileRepository.uniqueUsernameEmailPhone(username,email,phone);
    }
}
