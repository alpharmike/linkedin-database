package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.BackgroundTypeService;

import java.sql.SQLException;

public class BackgroundTypeRepository extends BaseTypeRepository<BackgroundType> implements BackgroundTypeService {

    public BackgroundTypeRepository() throws SQLException {
        super(BackgroundType.class);
    }
}
