package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.Type.BaseType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseTypeRepository extends BaseRepository<BaseType, Long> {
    public BaseTypeRepository(String tableName) throws SQLException {
        super(tableName);
    }

    @Override
    public BaseType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    void insert(BaseType object) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into ? (name) values (?)");
        ps.setString(1, this.getTableName());
        ps.setString(2, object.getName());
        ResultSet resultSet = ps.executeQuery();
    }
}
