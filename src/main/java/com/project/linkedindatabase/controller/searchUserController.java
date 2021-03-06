package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api")
public class searchUserController {

    private final ProfileService profileService;
    private final ConnectService connectService;
    private final BackgroundService backgroundService;

    public searchUserController(ProfileService profileService, ConnectService connectService, BackgroundService backgroundService) {
        this.profileService = profileService;
        this.connectService = connectService;
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

     /*
    name = key

     */

    @PostMapping("/search-name")
    public List<Profile> getBaseName(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String,Object> search) {
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
            String name = (String) search.get("name");
            return profileService.searchOtherBaseName(name);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    /*
    sortedName = key

     */
    @PostMapping("/search-sortedName")
    public List<Map<String,Object>> getBaseSortedName(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String,Object> search) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            System.out.println("CALLED SORTED NAME");
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {

            Long profileId = profile.getId();
            String name = (String) search.get("keyword");
            return connectService.searchBaseOfConnection(profileId,name);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @PostMapping("/search")
    public List<Profile> filterUsers(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String,Object> search) {
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
            String keyword = (String) search.get("keyword");
            String filter = (String) search.get("filter");

            switch (filter) {
                case "company":
                    return profileService.searchOtherBaseCurrentCompany(keyword);
                case "location":
                    return profileService.searchOtherBaseLocation(keyword);
                case "language":
                    return profileService.searchOtherBaseLanguage(keyword);
                case "name":
                    return profileService.searchOtherBaseName(keyword);

                default:
                    return new ArrayList<>();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }
    }
}
