package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FormerNameVisibilityTypeRepository extends BaseTypeRepository<FormerNameVisibilityType> {
    public FormerNameVisibilityTypeRepository() throws SQLException {
        super(FormerNameVisibilityType.class);
    }


    @Override
    public FormerNameVisibilityType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new FormerNameVisibilityType();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
