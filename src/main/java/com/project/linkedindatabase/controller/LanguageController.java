package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.accomplishment.Language;
import com.project.linkedindatabase.jsonToPojo.BackgroundJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.accomplishment.LanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class LanguageController {
    private final LanguageService languageService;
    private final ProfileService profileService ;

    public LanguageController(ProfileService profileService, LanguageService languageService) {
        this.languageService = languageService;
        this.profileService = profileService;

    }


    @GetMapping("/language")
    public List<Language> getLanguage(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return languageService.findByProfileId(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @PostMapping("/language")
    public void addLanguage(@RequestHeader Map<String, Object> jsonHeader,@RequestBody Language language) {
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
            language.setProfileId(profile.getId());
            languageService.save(language);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @PutMapping("/language/{id}")
    public void updateLanguage(@RequestHeader Map<String, Object> jsonHeader,@RequestBody Language language,@PathVariable(name = "id") Long id) {
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
            language.setProfileId(profile.getId());
            language.setId(id);
            languageService.updateWithProfileId(language);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @DeleteMapping("/language/{id}")
    public void deleteLanguage(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
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
            Language language = new Language();
            language.setProfileId(profile.getId());
            language.setId(id);
            languageService.deleteByIdAndProfileId(language);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }
}
