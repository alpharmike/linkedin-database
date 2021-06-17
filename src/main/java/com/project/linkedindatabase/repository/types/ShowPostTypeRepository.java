package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;

import java.sql.SQLException;

public class ShowPostTypeRepository extends BaseTypeRepository {
    public ShowPostTypeRepository() throws SQLException {
        super(ShowPostType.class);
    }
}
