package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.IndustryService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndustryRepository extends BaseTypeRepository<Industry> {
    public IndustryRepository() throws SQLException {
        super(Industry.class);
    }


    @Override
    public Industry convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new Industry();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
