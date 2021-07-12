package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.Type.BaseType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseTypeRepository<T extends BaseType> extends BaseRepository<T , Long>  {

    protected static final String NAME = "name";
    protected static final String ID = "id";


    public BaseTypeRepository(Class<?> type) throws SQLException {
        super(type);
    }




    @Override
    public void save(T object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("insert into " +this.getTableName() + " (" + NAME + ") values (?);");

        ps.setString(1, object.getName());
        ResultSet resultSet = ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "("
                + " " + ID + " bigint primary key not null auto_increment,"
                + " " + NAME + " nvarchar(100) not null"
                + ");"
        );
        ps.execute();
    }
}
