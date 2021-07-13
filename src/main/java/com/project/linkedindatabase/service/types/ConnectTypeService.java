package com.project.linkedindatabase.service.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.service.BaseTypeService;

import java.sql.SQLException;

public interface ConnectTypeService extends BaseTypeService<ConnectType> {
    public ConnectType defaultType() throws SQLException;
}
