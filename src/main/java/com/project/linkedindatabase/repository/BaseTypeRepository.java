package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.Type.BaseType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseTypeRepository<T extends BaseType> extends BaseRepository<T , Long>  {

    public BaseTypeRepository(Class<?> type) throws SQLException {
        super(type);
    }

    @Override
    public T convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    public void save(T object) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into ? (name) values (?);");
        ps.setString(1, this.getTableName());
        ps.setString(2, object.getName());
        ResultSet resultSet = ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        final String createQuery = String.format("create table if not exists %s ("
                + " id bigint not null auto_increment,"
                + " name nvarchar(100) not null,"
                + " primary key (id)"
                + ")", this.tableName);
        PreparedStatement ps = conn.prepareStatement(createQuery);
        ps.execute();
    }

}
