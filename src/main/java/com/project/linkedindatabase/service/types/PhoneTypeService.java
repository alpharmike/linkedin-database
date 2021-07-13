package com.project.linkedindatabase.service.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.service.BaseTypeService;

import java.sql.SQLException;

public interface PhoneTypeService extends BaseTypeService<PhoneType> {
    public PhoneType defaultType() throws SQLException;
}
