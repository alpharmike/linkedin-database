package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class IndustryRepository extends BaseTypeRepository {
    public IndustryRepository() throws SQLException {
        super(Industry.class);
    }
}
