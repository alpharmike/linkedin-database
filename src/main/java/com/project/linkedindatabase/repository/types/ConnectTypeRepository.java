package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ConnectTypeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectTypeRepository extends BaseTypeRepository<ConnectType> {
    public ConnectTypeRepository() throws SQLException {
        super(ConnectType.class);
    }

    @Override
    public ConnectType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new ConnectType();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
