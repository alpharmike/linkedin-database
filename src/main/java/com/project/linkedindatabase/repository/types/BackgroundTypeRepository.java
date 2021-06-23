package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.BackgroundTypeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BackgroundTypeRepository extends BaseTypeRepository<BackgroundType> {

    public BackgroundTypeRepository() throws SQLException {
        super(BackgroundType.class);
    }

    @Override
    public BackgroundType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new BackgroundType();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
