package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.PhoneTypeService;

import java.sql.SQLException;

public class PhoneTypeRepository extends BaseTypeRepository<PhoneType> {
    public PhoneTypeRepository() throws SQLException {
        super(PhoneType.class);
    }
}
