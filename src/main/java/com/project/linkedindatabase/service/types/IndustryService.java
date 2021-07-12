package com.project.linkedindatabase.service.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.service.BaseTypeService;

import java.sql.SQLException;

public interface IndustryService extends BaseTypeService<Industry> {
    public Industry defaultType() throws SQLException;
}
