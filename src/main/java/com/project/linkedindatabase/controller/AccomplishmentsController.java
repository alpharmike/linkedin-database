package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.jsonToPojo.AccomplishmentJson;
import com.project.linkedindatabase.jsonToPojo.BackgroundJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.accomplishment.AccomplishmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class AccomplishmentsController {

    private final AccomplishmentService accomplishmentService;
    private final ProfileService profileService ;

    public AccomplishmentsController(AccomplishmentService accomplishmentService, ProfileService profileService) {
        this.accomplishmentService = accomplishmentService;
        this.profileService = profileService;
    }

    @GetMapping("/accomplishment")
    public List<Accomplishment> getAccomplishment(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return accomplishmentService.findByProfileId(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/accomplishment-json")
    public List<AccomplishmentJson> getAccomplishmentJson(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return accomplishmentService.getAllByProfileIdJson(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }


    @GetMapping("/accomplishment/{id}")
    public List<Accomplishment> getAccomplishmentByProfileID(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            return accomplishmentService.findByProfileId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/accomplishment-json/{id}")
    public List<AccomplishmentJson> getAccomplishmentByProfileIDJson(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            return accomplishmentService.getAllByProfileIdJson(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @PostMapping("/accomplishment")
    public void addAccomplishment(@RequestHeader Map<String, Object> jsonHeader,@RequestBody Accomplishment accomplishment) {
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
            accomplishment.setProfileId(profile.getId());
            accomplishmentService.save(accomplishment);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @PutMapping("/accomplishment/{id}")
    public void updateAccomplishment(@RequestHeader Map<String, Object> jsonHeader,@RequestBody Accomplishment accomplishment,
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
            accomplishment.setId(id);
            accomplishment.setProfileId(profile.getId());
            accomplishmentService.updateWithProfileId(accomplishment);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @DeleteMapping("/accomplishment/{id}")
    public void deleteAccomplishment(@RequestHeader Map<String, Object> jsonHeader
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
            Accomplishment accomplishment = new Accomplishment();
            accomplishment.setId(id);
            accomplishment.setProfileId(profile.getId());
            accomplishmentService.deleteByIdAndProfileId(accomplishment);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }
}
