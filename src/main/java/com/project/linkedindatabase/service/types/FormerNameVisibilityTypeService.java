package com.project.linkedindatabase.service.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.service.BaseTypeService;

import java.sql.SQLException;

public interface FormerNameVisibilityTypeService extends BaseTypeService<FormerNameVisibilityType> {
    public FormerNameVisibilityType defaultType() throws SQLException;
}
