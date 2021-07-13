package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.config.JwtTokenUtil;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.*;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class TypeController {

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

    public TypeController(PhoneTypeService phoneTypeService, FormerNameVisibilityTypeService formerNameVisibilityTypeService,
                          IndustryService industryService, ConnectTypeService connectTypeService,
                          AccomplishmentTypeService accomplishmentTypeService, BackgroundTypeService backgroundTypeService,
                          SkillLevelService skillLevelService, NotificationTypeService notificationTypeService,
                          ShowPostTypeService showPostTypeService, LanguageLevelService languageLevelService,
                          RelationKnowledgeService relationKnowledgeService, ProfileService profileService) {
        this.phoneTypeService = phoneTypeService;
        this.formerNameVisibilityTypeService = formerNameVisibilityTypeService;
        this.industryService = industryService;
        this.connectTypeService = connectTypeService;
        this.accomplishmentTypeService = accomplishmentTypeService;
        this.backgroundTypeService = backgroundTypeService;
        this.skillLevelService = skillLevelService;
        this.notificationTypeService = notificationTypeService;
        this.showPostTypeService = showPostTypeService;
        this.languageLevelService = languageLevelService;
        this.relationKnowledgeService = relationKnowledgeService;
        this.profileService = profileService;
    }


    @GetMapping("/phone-type")
    public List<PhoneType> getPhoneType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return phoneTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/industry-type")
    public List<Industry> getIndustryType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return industryService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/former-name-visibility-type")
    public List<FormerNameVisibilityType> getFormerNameVisibilityType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return formerNameVisibilityTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/connect-type")
    public List<ConnectType> getConnectType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return connectTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/accomplishment-type-service")
    public List<AccomplishmentType> getAccomplishmentType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return accomplishmentTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/background-type")
    public List<BackgroundType> getBackgroundType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return backgroundTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/skill-level")
    public List<SkillLevel> getSkillLevel(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return skillLevelService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/notification-type")
    public List<NotificationType> getNotificationType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return notificationTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/show-post-type")
    public List<ShowPostType> getShowPostType(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return showPostTypeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/language-level")
    public List<LanguageLevel> getLanguageLevel(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return languageLevelService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/relation-knowledge")
    public List<RelationKnowledge> getRelationKnowledge(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return relationKnowledgeService.findAll();
        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }
}
