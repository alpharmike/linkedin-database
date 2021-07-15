package com.project.linkedindatabase.bootstrap;


import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import com.project.linkedindatabase.service.model.post.PostService;
import com.project.linkedindatabase.service.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {



    private final PhoneTypeService phoneTypeService;
    private final FormerNameVisibilityTypeService formerNameVisibilityTypeService;
    private final IndustryService industryService;
    private final ConnectTypeService connectTypeService;
    private final AccomplishmentTypeService accomplishmentTypeService;
    private final BackgroundTypeService backgroundTypeService;
    private final SkillLevelService skillLevelService;
    private final NotificationTypeService notificationTypeService;
    private final ShowPostTypeService showPostTypeService;
    private final LanguageLevelService languageLevelService;
    private final RelationKnowledgeService relationKnowledgeService;


    private final ProfileService profileService;
    private final BackgroundService backgroundService;
    private final AccomplishmentService accomplishmentService;
    private final LanguageService languageService;
    private final ConnectService connectService;

    private final ChatService chatService;
    private final MessageService messageService;

    private final LikeCommentService likeCommentService;
    private final CommentService commentService;
    private final LikePostService likePostService;
    private final PostService postService;


    public Bootstrap(ProfileService profileService, PhoneTypeService phoneTypeService,
                     FormerNameVisibilityTypeService formerNameVisibilityTypeService, IndustryService industryService,
                     ConnectTypeService connectTypeService, AccomplishmentTypeService accomplishmentTypeService,
                     BackgroundTypeService backgroundTypeService, SkillLevelService skillLevelService,
                     NotificationTypeService notificationTypeService, ShowPostTypeService showPostTypeService,
                     LanguageLevelService languageLevelService, RelationKnowledgeService relationKnowledgeService,
                     BackgroundService backgroundService, AccomplishmentService accomplishmentService
            , LanguageService languageService, ConnectService connectService, ChatService chatService,
                     MessageService messageService, LikeCommentService likeCommentService,
                     CommentService commentService, LikePostService likePostService, PostService postService) {



        this.phoneTypeService = phoneTypeService;
        this.formerNameVisibilityTypeService = formerNameVisibilityTypeService;
        this.connectTypeService = connectTypeService;
        this.industryService = industryService;
        this.accomplishmentTypeService = accomplishmentTypeService;
        this.backgroundTypeService = backgroundTypeService;
        this.skillLevelService = skillLevelService;
        this.notificationTypeService = notificationTypeService;
        this.showPostTypeService = showPostTypeService;
        this.languageLevelService = languageLevelService;
        this.relationKnowledgeService = relationKnowledgeService;


        this.profileService = profileService;
        this.backgroundService = backgroundService;
        this.accomplishmentService = accomplishmentService;
        this.languageService = languageService;
        this.connectService = connectService;
        this.chatService = chatService;
        this.messageService = messageService;
        this.likeCommentService = likeCommentService;
        this.commentService = commentService;
        this.likePostService = likePostService;
        this.postService = postService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("Application Started");
        log.info(BaseEntity.getTableName(Profile.class));
       try {


           this.phoneTypeService.createTable();
           this.formerNameVisibilityTypeService.createTable();
           this.connectTypeService.createTable();
           this.industryService.createTable();
           this.accomplishmentTypeService.createTable();
           this.backgroundTypeService.createTable();
           this.skillLevelService.createTable();
           this.notificationTypeService.createTable();
           this.showPostTypeService.createTable();
           this.languageLevelService.createTable();
           this.relationKnowledgeService.createTable();




           this.profileService.createTable();
           this.backgroundService.createTable();
           this.accomplishmentService.createTable();
           this.languageService.createTable();
           this.connectService.createTable();

           this.chatService.createTable();
           this.messageService.createTable();

           this.postService.createTable();
           this.likePostService.createTable();
           this.commentService.createTable();
           this.likeCommentService.createTable();
//            new PhoneTypeRepository().createTable();
//            new FormerNameVisibilityTypeRepository().createTable();
//            new IndustryRepository().createTable();
//            new ProfileRepository().createTable();
//            new AccomplishmentTypeRepository().createTable();
//            new ConnectTypeRepository().createTable();
//            new BackgroundTypeRepository().createTable();
//            new LanguageLevelRepository().createTable();
//            new RelationKnowledgeRepository().createTable();
//            new ShowPostTypeRepository().createTable();
//            new SkillLevelRepository().createTable();
//            new SkillRepository().createTable();
//            new EndorsementRepository().createTable();
//            new PostRepository().createTable();
//            new CommentRepository().createTable();
//            new LikePostRepository().createTable();
//            new LikeCommentRepository().createTable();
//
           addType();
      } catch (Exception throwables) {

           throwables.printStackTrace();
       }
    }

    private void addType() throws Exception {
        List<String> accomplishmentType = SaveTypes.readAccomplishmentType();
        for (String name : accomplishmentType)
        {
            accomplishmentTypeService.saveIfNotExist(name);
        }

        List<String> backgroundType = SaveTypes.readBackgroundType();
        for (String name : backgroundType)
        {
            backgroundTypeService.saveIfNotExist(name);
        }

        List<String> connectType = SaveTypes.readConnectType();
        for (String name : connectType)
        {
            System.out.println(name);
            connectTypeService.saveIfNotExist(name);
        }


        List<String> skillLevel = SaveTypes.readSkillLevel();
        for (String name : skillLevel)
        {
            skillLevelService.saveIfNotExist(name);
        }

        List<String> formerNameVisibilityType = SaveTypes.readFormerNameVisibilityType();
        for (String name : formerNameVisibilityType)
        {
            formerNameVisibilityTypeService.saveIfNotExist(name);
        }

        List<String>  industryType = SaveTypes.readIndustryType();
        for (String name :  industryType)
        {
            industryService.saveIfNotExist(name);
        }

        List<String> languageLevel = SaveTypes.readLanguageLevel();
        for (String name : languageLevel)
        {
            languageLevelService.saveIfNotExist(name);
        }

        List<String> notificationType = SaveTypes.readNotificationType();
        for (String name : notificationType)
        {
            notificationTypeService.saveIfNotExist(name);
        }


        List<String> phoneType = SaveTypes.readPhoneType();
        for (String name : phoneType)
        {
            phoneTypeService.saveIfNotExist(name);
        }


        List<String> readRelationKnowledge = SaveTypes.readRelationKnowledge();
        for (String name : readRelationKnowledge)
        {
            relationKnowledgeService.saveIfNotExist(name);
        }




        List<String> showPostType = SaveTypes.readShowPostType();
        for (String name : showPostType)
        {
            showPostTypeService.saveIfNotExist(name);
        }
    }
}
