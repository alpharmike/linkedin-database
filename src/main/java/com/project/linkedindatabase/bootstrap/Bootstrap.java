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
        try {
            new PhoneTypeRepository().createTable();
            new FormerNameVisibilityTypeRepository().createTable();
            new CurrentP
            new ProfileRepository().createTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
