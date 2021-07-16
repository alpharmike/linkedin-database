package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Profile;
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
public class searchUserController {

    private final ProfileService profileService;
    private final BackgroundService backgroundService;

    public searchUserController(ProfileService profileService, BackgroundService backgroundService) {
        this.profileService = profileService;
        this.backgroundService = backgroundService;
    }

    /*
    companyName = key

     */
    @PostMapping("/search-current-company")
    public List<Profile> getCurrentComp(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String,Object> search) {
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

            Long profileId = profile.getId();
            String companyName = (String) search.get("companyName");
            return profileService.searchOtherBaseCurrentCompany(companyName);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }


    /*
    location = key

     */
    @PostMapping("/search-location")
    public List<Profile> getBaseLocation(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String,Object> search) {
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

            Long profileId = profile.getId();
            String location = (String) search.get("location");
            return profileService.searchOtherBaseLocation(location);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }


    @PostMapping("/search-language")
    public List<Profile> getBaseLanguage(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String,Object> search) {
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

            Long profileId = profile.getId();
            String language = (String) search.get("language");
            return profileService.searchOtherBaseLanguage(language);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }
}
