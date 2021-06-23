package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ShowPostTypeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowPostTypeRepository extends BaseTypeRepository<ShowPostType> {
    public ShowPostTypeRepository() throws SQLException {
        super(ShowPostType.class);
    }




    @Override
    public ShowPostType convertSql(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(NAME);
        Long id = resultSet.getLong(ID);
        var type = new ShowPostType();
        type.setId(id);
        type.setName(name);

        return type;
    }
}
