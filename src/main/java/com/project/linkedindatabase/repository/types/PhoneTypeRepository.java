package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.PhoneType;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class PhoneTypeRepository extends BaseTypeRepository {
    public PhoneTypeRepository() throws SQLException {
        super(PhoneType.class);
    }
}
