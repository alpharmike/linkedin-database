package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.FormerNameVisibilityTypeService;

import java.sql.SQLException;

public class FormerNameVisibilityTypeRepository extends BaseTypeRepository<FormerNameVisibilityType> implements FormerNameVisibilityTypeService {
    public FormerNameVisibilityTypeRepository() throws SQLException {
        super(FormerNameVisibilityType.class);
    }
}
