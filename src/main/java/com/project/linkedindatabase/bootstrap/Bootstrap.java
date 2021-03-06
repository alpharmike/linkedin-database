package com.project.linkedindatabase.bootstrap;


import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
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
import com.project.linkedindatabase.service.model.skill.SkillService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import com.project.linkedindatabase.service.types.*;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.*;

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

    private final SkillService skillService;
    private final EndorsementService endorsementService;

    private NotificationService notificationService;


    public Bootstrap(ProfileService profileService, PhoneTypeService phoneTypeService,
                     FormerNameVisibilityTypeService formerNameVisibilityTypeService, IndustryService industryService
            , ConnectTypeService connectTypeService, AccomplishmentTypeService accomplishmentTypeService,
                     BackgroundTypeService backgroundTypeService, SkillLevelService skillLevelService,
                     NotificationTypeService notificationTypeService, ShowPostTypeService showPostTypeService,
                     LanguageLevelService languageLevelService, RelationKnowledgeService relationKnowledgeService,
                     BackgroundService backgroundService, AccomplishmentService accomplishmentService,
                     LanguageService languageService, ConnectService connectService, ChatService chatService,
                     MessageService messageService, LikeCommentService likeCommentService,
                     CommentService commentService, LikePostService likePostService, PostService postService,
                     SkillService skillService, EndorsementService endorsementService,
                     NotificationService notificationService) {


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
        this.skillService = skillService;
        this.endorsementService = endorsementService;
        this.notificationService = notificationService;
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
            this.skillService.createTable();
            this.languageService.createTable();
            this.connectService.createTable();
            this.endorsementService.createTable();
            this.chatService.createTable();
            this.messageService.createTable();

            this.postService.createTable();
            this.commentService.createTable();
            this.likeCommentService.createTable();
            this.likePostService.createTable();

            this.notificationService.createTable();

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

        TimerTask birthDay = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (isValidDate()) {
                        notificationService.createBirthDayNotification();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(birthDay, 0, 24 * 60 * 60 * 1000);
    }

    private boolean isValidDate() {
        String directoryPath = "src/main/data/dates/";
        String filepath = directoryPath + "dates.txt";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        Path path = Paths.get(filepath);
        String text = DateConverter.convertDate(DateConverter.getToday(), "yyy-MM-dd");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND,
                StandardOpenOption.CREATE)) {
            if (existInFile(filepath, text)) {
                return false;
            }
            writer.write(text + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean existInFile(String filepath, String target) throws FileNotFoundException {
        File file = new File(filepath);
        if (!file.exists()) {
            return false;
        }
        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            final String lineFromFile = scanner.nextLine();
            System.out.println(lineFromFile);
            if (lineFromFile.contains(target)) {
                return true;
            }
        }

        return false;
    }

    private void addType() throws Exception {
        List<String> accomplishmentType = SaveTypes.readAccomplishmentType();
        for (String name : accomplishmentType) {
            accomplishmentTypeService.saveIfNotExist(name);
        }

        List<String> backgroundType = SaveTypes.readBackgroundType();
        for (String name : backgroundType) {
            backgroundTypeService.saveIfNotExist(name);
        }

        List<String> connectType = SaveTypes.readConnectType();
        for (String name : connectType) {

            connectTypeService.saveIfNotExist(name);
        }


        List<String> skillLevel = SaveTypes.readSkillLevel();
        for (String name : skillLevel) {
            skillLevelService.saveIfNotExist(name);
        }

        List<String> formerNameVisibilityType = SaveTypes.readFormerNameVisibilityType();
        for (String name : formerNameVisibilityType) {
            formerNameVisibilityTypeService.saveIfNotExist(name);
        }

        List<String> industryType = SaveTypes.readIndustryType();
        for (String name : industryType) {
            industryService.saveIfNotExist(name);
        }

        List<String> languageLevel = SaveTypes.readLanguageLevel();
        for (String name : languageLevel) {
            languageLevelService.saveIfNotExist(name);
        }

        List<String> notificationType = SaveTypes.readNotificationType();
        for (String name : notificationType) {
            notificationTypeService.saveIfNotExist(name);
        }


        List<String> phoneType = SaveTypes.readPhoneType();
        for (String name : phoneType) {
            phoneTypeService.saveIfNotExist(name);
        }


        List<String> readRelationKnowledge = SaveTypes.readRelationKnowledge();
        for (String name : readRelationKnowledge) {
            relationKnowledgeService.saveIfNotExist(name);
        }


        List<String> showPostType = SaveTypes.readShowPostType();
        for (String name : showPostType) {
            showPostTypeService.saveIfNotExist(name);
        }
    }
}
