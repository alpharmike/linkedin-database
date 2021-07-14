package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ConnectTypeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectTypeRepository extends BaseTypeRepository<ConnectType> {
    public ConnectTypeRepository() throws SQLException {
        super(ConnectType.class);
    }

    public ConnectType defaultType() throws SQLException {
        String name = "pending";
        return super.findByName(name);
    }

    public ConnectType getByType(String type) throws SQLException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE name=?");
        retrievePs.setString(1, type);
        ResultSet resultSet = retrievePs.executeQuery();

        if (!resultSet.isBeforeFirst())
        {
            return null;
        }
        resultSet.next();
        return this.convertSql(resultSet);
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
