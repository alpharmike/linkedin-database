package com.project.linkedindatabase.rest;

import com.project.linkedindatabase.service.BaseTypeService;
import com.project.linkedindatabase.service.typesmap.AccomplishmentTypeServiceMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("api/")
public class InitializerController {
    private final AccomplishmentTypeServiceMap accomplishmentTypeService;

    public InitializerController() throws SQLException {
        this.accomplishmentTypeService = new AccomplishmentTypeServiceMap();
    }

    @GetMapping("init")
    public void initialize() throws SQLException {
        this.accomplishmentTypeService.createTable();
    }


}
