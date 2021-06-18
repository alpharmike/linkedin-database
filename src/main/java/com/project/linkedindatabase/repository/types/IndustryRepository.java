package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.Industry;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.IndustryService;

import java.sql.SQLException;

public class IndustryRepository extends BaseTypeRepository<Industry> implements IndustryService {
    public IndustryRepository() throws SQLException {
        super(Industry.class);
    }
}
