package com.project.linkedindatabase.service.model;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public interface ProfileService extends BaseService<Profile,Long> {

    public boolean uniqueUsernameEmailPhone(String username,String email,String phone) throws SQLException;
    public Profile findByUsername(String username) throws SQLException;

    public List<Profile> searchOtherBaseCurrentCompany(Long id,String companyName) throws Exception;

    public void update(Profile profileForUpdate) throws Exception;

    public List<Profile> searchOtherBaseLanguage(Long profileId, String language) throws SQLException;
}
