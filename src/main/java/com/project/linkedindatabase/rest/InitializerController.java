package com.project.linkedindatabase.rest;

import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import com.project.linkedindatabase.service.model.post.PostService;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import com.project.linkedindatabase.service.modelMap.skill.SkillServiceMap;
import com.project.linkedindatabase.service.types.*;
import com.project.linkedindatabase.service.typesmap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("api/")
public class InitializerController {
    private final AccomplishmentTypeService accomplishmentTypeService;
    private final BackgroundTypeService backgroundTypeService;
    private final ConnectTypeService connectTypeService;
    private final FormerNameVisibilityTypeService formerNameVisibilityTypeService;
    private final IndustryService industryService;
    private final LanguageLevelService languageLevelService;
    private final NotificationTypeService notificationTypeService;
    private final PhoneTypeService phoneTypeService;
    private final RelationKnowledgeService relationKnowledgeService;
    private final ShowPostTypeService showPostTypeService;
    private final SkillLevelService skillLevelService;


    private final AccomplishmentService accomplishmentService;
    private final LanguageService languageService;

    private final ChatService chatService;
    private final MessageService messageService;

    private final PostService postService;
    private final LikePostService likePostService;
    private final LikeCommentService likeCommentService;
    private final CommentService commentService;



    private final SkillServiceMap skillServiceMap;
    private final EndorsementService endorsementService;

    private final ProfileService profileService;
    private final BackgroundService backgroundService;
    private final NotificationService notificationService;
    private final ConnectService connectService;


    @Autowired
    public InitializerController(AccomplishmentTypeServiceMap accomplishmentTypeService,
                                 BackgroundTypeServiceMap backgroundTypeService,
                                 ConnectTypeServiceMap connectTypeService,
                                 FormerNameVisibilityTypeServiceMap formerNameVisibilityTypeService,
                                 IndustryServiceMap industryService, LanguageLevelServiceMap languageLevelService,
                                 NotificationTypeServiceMap notificationTypeService,
                                 PhoneTypeServiceMap phoneTypeService,
                                 RelationKnowledgeServiceMap relationKnowledgeService,
                                 ShowPostTypeServiceMap showPostTypeService,
                                 SkillLevelServiceMap skillLevelService,
                                 AccomplishmentService accomplishmentService,
                                 LanguageService languageService,
                                 ChatService chatService,
                                 MessageService messageService,
                                 PostService postService,
                                 LikePostService likePostService,
                                 LikeCommentService likeCommentService,
                                 CommentService commentService,
                                 SkillServiceMap skillServiceMap,
                                 EndorsementService endorsementService,
                                 ProfileService profileService,
                                 BackgroundService backgroundService,
                                 NotificationService notificationService,
                                 ConnectService connectService) throws SQLException {

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
        this.accomplishmentService = accomplishmentService;
        this.languageService = languageService;
        this.chatService = chatService;
        this.messageService = messageService;
        this.postService = postService;
        this.likePostService = likePostService;
        this.likeCommentService = likeCommentService;
        this.commentService = commentService;
        this.skillServiceMap = skillServiceMap;
        this.endorsementService = endorsementService;
        this.profileService = profileService;
        this.backgroundService = backgroundService;
        this.notificationService = notificationService;
        this.connectService = connectService;
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

        this.accomplishmentService.createTable();
        this.languageService.createTable();
        this.chatService.createTable();
        this.messageService.createTable();
        this.postService.createTable();
        this.likePostService.createTable();
        this.likeCommentService.createTable();
        this.commentService.createTable();
        this.skillServiceMap.createTable();
        this.endorsementService.createTable();
        this.profileService.createTable();
        this.backgroundService.createTable();
        this.notificationService.createTable();
        this.connectService.createTable();






    }

}