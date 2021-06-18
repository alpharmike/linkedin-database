package com.project.linkedindatabase.repository.types;

import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.repository.BaseTypeRepository;
import com.project.linkedindatabase.service.types.ShowPostTypeService;

import java.sql.SQLException;

public class ShowPostTypeRepository extends BaseTypeRepository<ShowPostType> implements ShowPostTypeService {
    public ShowPostTypeRepository() throws SQLException {
        super(ShowPostType.class);
    }
}
