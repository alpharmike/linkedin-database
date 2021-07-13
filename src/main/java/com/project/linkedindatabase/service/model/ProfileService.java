package com.project.linkedindatabase.service.model;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;

public interface ProfileService extends BaseService<Profile,Long> {

    public boolean uniqueUsernameEmailPhone(String username,String email,String phone) throws SQLException;
    public Profile findByUsername(String username) throws SQLException;
}
