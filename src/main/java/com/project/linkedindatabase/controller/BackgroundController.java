package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.BackgroundJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.types.BackgroundTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class BackgroundController {

    private final BackgroundService backgroundService;
    private final ProfileService profileService ;
    private final NotificationService notificationService;
    private final BackgroundTypeService backgroundTypeService;

    public BackgroundController(BackgroundService backgroundService, ProfileService profileService, NotificationService notificationService, BackgroundTypeService backgroundTypeService) {
        this.backgroundService = backgroundService;
        this.profileService = profileService;
        this.notificationService = notificationService;
        this.backgroundTypeService = backgroundTypeService;
    }

    @GetMapping("/background")
    public List<Background> getBackground(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return backgroundService.findByProfileId(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/background-json")
    public List<BackgroundJson> getBackgroundJson(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            List<BackgroundJson> backgroundJsonList = backgroundService.findByProfileIdJson(profile.getId());
            return backgroundJsonList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/background/{id}")
    public List<Background> getBackGroundByProfileId(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return backgroundService.findByProfileId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/background-json/{id}")
    public List<BackgroundJson> getBackgroundJsonByProfileId(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            List<BackgroundJson> backgroundJsonList = backgroundService.findByProfileIdJson(id);
            return backgroundJsonList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @PostMapping("/background")
    public void addBackGround(@RequestHeader Map<String, Object> jsonHeader,@RequestBody BackgroundJson backgroundJson) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {
            backgroundJson.setProfileId(profile.getId());
            Background background = backgroundJson.convertToBackGround();
            backgroundService.save(background);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @PutMapping("/background/{id}")
    public void updateBackground(@RequestHeader Map<String, Object> jsonHeader,@RequestBody BackgroundJson backgroundJson
            ,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {
            backgroundJson.setProfileId(profile.getId());
            Background background = backgroundJson.convertToBackGround();
            background.setId(id);
            Background oldBackGround = backgroundService.findById(id);
            backgroundService.updateWithProfileId(background);
            if (oldBackGround.getBackgroundType() == backgroundTypeService.findByName("Work experience").getId() &&
                    !oldBackGround.getTitle().equals(background.getTitle())
            ){
                notificationService.saveChangedWorkExperienceNotification(profile.getId());}
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @DeleteMapping("/background/{id}")
    public void deleteBackground(@RequestHeader Map<String, Object> jsonHeader,
                                 @PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {
            Background background = new Background();
            background.setProfileId(profile.getId());
            background.setId(id);
            backgroundService.deleteByIdAndProfileId(background);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }
}
