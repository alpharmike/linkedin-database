package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class ConnectTypeRepository extends BaseTypeRepository {
    public ConnectTypeRepository() throws SQLException {
        super(ConnectType.class);
    }
}
