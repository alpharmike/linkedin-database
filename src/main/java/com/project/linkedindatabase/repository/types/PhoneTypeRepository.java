package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.PhoneTypeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneTypeRepository extends BaseTypeRepository<PhoneType> {
    public PhoneTypeRepository() throws SQLException {
        super(PhoneType.class);
    }


    @Override
    public PhoneType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new PhoneType();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
