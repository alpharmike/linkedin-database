package com.project.linkedindatabase.service.model;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ProfileService extends BaseService<Profile,Long> {

    public boolean uniqueUsernameEmailPhone(String username,String email,String phone) throws SQLException;
    public Profile findByUsername(String username) throws SQLException;

    public List<Profile> searchOtherBaseCurrentCompany(String companyName) throws Exception;

    public void update(Profile profileForUpdate) throws Exception;

    public List<Profile> searchOtherBaseName(String name)throws Exception;
    public List<Profile> searchOtherBaseLanguage( String language) throws SQLException;
    public List<Profile> searchOtherBaseLocation( String location) throws SQLException;
    public ProfileJson getProfileByIdJson(Long id) throws Exception;

    public void setCurrentEducation(Profile profile) throws Exception ;
    public void removeCurrentPosition(Profile profile) throws Exception ;
    public void removeCurrentEducation(Profile profile) throws Exception ;
    public void setCurrentPosition(Profile profile) throws Exception;

}
