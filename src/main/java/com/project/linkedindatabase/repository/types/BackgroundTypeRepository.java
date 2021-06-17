package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class BackgroundTypeRepository extends BaseTypeRepository {

    public BackgroundTypeRepository() throws SQLException {
        super(BackgroundType.class);
    }
}
