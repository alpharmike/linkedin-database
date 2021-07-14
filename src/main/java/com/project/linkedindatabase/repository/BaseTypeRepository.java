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


    public T findByName(String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName()+" where "+NAME+" = ?");

        ps.setString(1, name);

        ResultSet resultSet = ps.executeQuery();
        if (!resultSet.isBeforeFirst())
        {
            return null;
        }
        resultSet.next();
        return convertSql(resultSet);
    }

    public void saveIfNotExist(String name) throws SQLException {

        T obj = findByName(name);
        if (obj == null) {
            PreparedStatement ps = this.conn.prepareStatement("insert into " + this.getTableName() + " (" + NAME + ") values (?) ;");

            ps.setString(1, name);

            ps.execute();
        }
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
