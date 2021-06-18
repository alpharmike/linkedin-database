package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ConnectTypeService;

import java.sql.SQLException;

public class ConnectTypeRepository extends BaseTypeRepository<ConnectType> {
    public ConnectTypeRepository() throws SQLException {
        super(ConnectType.class);
    }
}
