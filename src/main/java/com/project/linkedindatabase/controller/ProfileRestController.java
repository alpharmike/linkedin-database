package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProfileRestController {

    private final ProfileService profileService ;

    public ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profile")
    public Profile getProfile(@RequestHeader Map<String, Object> jsonHeader){
        log.info(jsonHeader.toString());
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }

        return profile;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profile-json")
    public ProfileJson getProfileJson(@RequestHeader Map<String, Object> jsonHeader){
        log.info(jsonHeader.toString());
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }
        ProfileJson profileJson;
        try {
            profileJson = profileService.getProfileByIdJson(profile.getId());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }

        return profileJson;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profile-json/{id}")
    public ProfileJson getProfileJsonById(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id){
        log.info(jsonHeader.toString());
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }
        ProfileJson profileJson;
        try {
            profileJson = profileService.getProfileByIdJson(id);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }

        return profileJson;
    }

    @PostMapping("/sign-up")
    public Profile newEmployee(@RequestBody ProfileJson profileJson) throws SQLException {
        Profile profile;
        try {

            Calendar calendar = DateConverter.parse(profileJson.getDateOfBirth(), "yyyy-MM-dd");
            profile = profileJson.convertToProfile(true);
            profile.setDateOfBirth(calendar);
            profile.setUrlToProfile(profile.getUsername());


        }catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There was a problem in data you have are sent ",e);

        }
        boolean notUnique = false;
        try {
            if (!profileService.uniqueUsernameEmailPhone(profile.getUsername(), profile.getEmail(), profile.getPhoneNumber()))
            {
                notUnique = true;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ",e);
        }

        if(notUnique)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "username or email or phone number isn't unique ",new Exception("duplication"));
        }

        try {
            profileService.save(profile);
            profile = profileService.findByUsername(profile.getUsername());
        } catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "there some problem  ",new Exception("unknown problem"));
        }


        return profile;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/profile")
    public void updateProfile(@RequestHeader Map<String, Object> jsonHeader, @RequestBody ProfileJson data){
        log.info(jsonHeader.toString());
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }

        try {
            Profile profileForUpdate = data.convertToProfile();
            profileForUpdate.setId(profile.getId());
            Calendar calendar = DateConverter.parse(data.getDateOfBirth(), "yyyy-MM-dd");
            profileForUpdate.setDateOfBirth(calendar);
            profileService.update(profileForUpdate);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-profile/id/{id}")
    public Profile getProfileById(@RequestHeader Map<String, Object> jsonHeader, @PathVariable(name = "id") Long id){
        log.info(jsonHeader.toString());
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }

        try {

            return  profileService.findById(id);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @GetMapping("/get-profile/username/{username}")
    public Profile getProfileByUsername(@RequestHeader Map<String, Object> jsonHeader, @PathVariable(name = "username") String userName){
        log.info(jsonHeader.toString());
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }

        try {

            return  profileService.findByUsername(userName);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }


}
