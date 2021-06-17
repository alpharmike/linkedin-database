package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.FormerNameVisibilityType;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class FormerNameVisibilityTypeRepository extends BaseTypeRepository {
    public FormerNameVisibilityTypeRepository() throws SQLException {
        super(FormerNameVisibilityType.class);
    }
}
