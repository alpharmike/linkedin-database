package com.project.linkedindatabase.repository;

import com.project.linkedindatabase.domain.Type.BaseType;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.BaseTypeService;
import com.project.linkedindatabase.utils.AnnotationValueGetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseTypeRepository extends BaseRepository<BaseType, Long> {

    public BaseTypeRepository(Class<?> type) throws SQLException {
        super(type);
    }

    @Override
    public BaseType convertSql(ResultSet resultSet) {
        return null;
    }

    @Override
    void save(BaseType object) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into ? (name) values (?)");
        ps.setString(1, this.getTableName());
        ps.setString(2, object.getName());
        ResultSet resultSet = ps.executeQuery();
    }
}