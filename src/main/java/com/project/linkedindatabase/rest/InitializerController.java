package com.project.linkedindatabase.rest;

import com.project.linkedindatabase.service.BaseTypeService;
import com.project.linkedindatabase.service.typesmap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("api/")
public class InitializerController {
    private final AccomplishmentTypeServiceMap accomplishmentTypeService;
    private final BackgroundTypeServiceMap backgroundTypeService;
    private final ConnectTypeServiceMap connectTypeService;
    private final FormerNameVisibilityTypeServiceMap formerNameVisibilityTypeService;
    private final IndustryServiceMap industryService;
    private final LanguageLevelServiceMap languageLevelService;
    private final NotificationTypeServiceMap notificationTypeService;
    private final PhoneTypeServiceMap phoneTypeService;
    private final RelationKnowledgeServiceMap relationKnowledgeService;
    private final ShowPostTypeServiceMap showPostTypeService;
    private final SkillLevelServiceMap skillLevelService;

    @Autowired
    public InitializerController(AccomplishmentTypeServiceMap accomplishmentTypeService,
                                 BackgroundTypeServiceMap backgroundTypeService,
                                 ConnectTypeServiceMap connectTypeService,
                                 FormerNameVisibilityTypeServiceMap formerNameVisibilityTypeService,
                                 IndustryServiceMap industryService, LanguageLevelServiceMap languageLevelService,
                                 NotificationTypeServiceMap notificationTypeService,
                                 PhoneTypeServiceMap phoneTypeService,
                                 RelationKnowledgeServiceMap relationKnowledgeService,
                                 ShowPostTypeServiceMap showPostTypeService, SkillLevelServiceMap skillLevelService) throws SQLException {

        this.accomplishmentTypeService = accomplishmentTypeService;
        this.backgroundTypeService = backgroundTypeService;
        this.connectTypeService = connectTypeService;
        this.formerNameVisibilityTypeService = formerNameVisibilityTypeService;
        this.industryService = industryService;
        this.languageLevelService = languageLevelService;
        this.notificationTypeService = notificationTypeService;
        this.phoneTypeService = phoneTypeService;
        this.relationKnowledgeService = relationKnowledgeService;
        this.showPostTypeService = showPostTypeService;
        this.skillLevelService = skillLevelService;
    }

    @GetMapping("init")
    public void initialize() throws SQLException {
        this.accomplishmentTypeService.createTable();
        this.backgroundTypeService.createTable();
        this.connectTypeService.createTable();
        this.formerNameVisibilityTypeService.createTable();
        this.industryService.createTable();
        this.languageLevelService.createTable();
        this.notificationTypeService.createTable();
        this.phoneTypeService.createTable();
        this.relationKnowledgeService.createTable();
        this.showPostTypeService.createTable();
        this.skillLevelService.createTable();
    }

}