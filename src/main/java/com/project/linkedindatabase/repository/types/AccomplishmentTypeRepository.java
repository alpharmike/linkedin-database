package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.AccomplishmentType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.AccomplishmentTypeService;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AccomplishmentTypeRepository extends BaseTypeRepository<AccomplishmentType> {
    public AccomplishmentTypeRepository() throws SQLException {
        super(AccomplishmentType.class);
    }

    @Override
    public AccomplishmentType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new AccomplishmentType();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
