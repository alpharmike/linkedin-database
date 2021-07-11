package com.project.linkedindatabase.bootstrap;


import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.repository.model.ProfileRepository;
import com.project.linkedindatabase.repository.model.post.CommentRepository;
import com.project.linkedindatabase.repository.model.post.PostRepository;
import com.project.linkedindatabase.repository.types.AccomplishmentTypeRepository;
import com.project.linkedindatabase.repository.types.FormerNameVisibilityTypeRepository;
import com.project.linkedindatabase.repository.types.PhoneTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("Application Started");
        log.info(BaseEntity.getTableName(Profile.class));
//        try {
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
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}
