package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.jsonToPojo.BackgroundJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.ProfileService;
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

    public BackgroundController(BackgroundService backgroundService, ProfileService profileService) {
        this.backgroundService = backgroundService;
        this.profileService = profileService;
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

    @PostMapping("/background")
    public void addBackGround(@RequestHeader Map<String, Object> jsonHeader,@RequestBody BackgroundJson backgroundJson) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {
            Background background = backgroundJson.convertToBackGround();
            backgroundService.save(background);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @PutMapping("/background")
    public void updateBackground(@RequestHeader Map<String, Object> jsonHeader,@RequestBody BackgroundJson backgroundJson) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {
            Background background = backgroundJson.convertToBackGround();
            backgroundService.update(background);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @DeleteMapping("/background")
    public void deleteBackground(@RequestHeader Map<String, Object> jsonHeader,@RequestBody BackgroundJson backgroundJson) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {

            backgroundService.deleteById(backgroundJson.getId());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }
}
