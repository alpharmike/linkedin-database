package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Endorsement;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class EndorsementController {
    private final EndorsementService endorsementService;
    private final ProfileService profileService;
    private final NotificationService notificationService;

    public EndorsementController(EndorsementService endorsementService, ProfileService profileService, NotificationService notificationService) {
        this.endorsementService = endorsementService;
        this.profileService = profileService;
        this.notificationService = notificationService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/endorsement")
    public Endorsement get(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return endorsementService.findById((long) jsonBody.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/endorsement")
    public void save(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Endorsement endorsement){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            endorsement.setEndorserId(profile.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ",e);
        }
        boolean duplicate = false;

        try {
            System.out.println("HERE");
            if(endorsementService.isThereAnotherEndorsement(endorsement))
            {
                System.out.println("THHERE");
                duplicate = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ",e);
        }
        if (duplicate)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "You have already endorsed it before ",new Exception("duplicate"));
        }

        try {
            endorsementService.save(endorsement);
//            notificationService.saveEndorsementNotification(endorsement.getEndorserId(), endorsement.getSkillId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ",e);
        }



    }

    @CrossOrigin(origins = "*")
    @PutMapping("/endorsement/{id}")
    public void edit(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Endorsement endorsement,
                     @PathVariable long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            endorsement.setEndorserId(profile.getId());
            endorsement.setId(id);

            endorsementService.updateWithProfileId(endorsement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/endorsement/{id}")
    public void delete(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody,
                       @PathVariable long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            endorsementService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/endorsements/{id}")
    public List<Endorsement> getAllEndorsementsBySkillId(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody,
                                                         @PathVariable long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return endorsementService.getAllById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/endorsements-profile/{profileId}")
    public List<Endorsement> getAllEndorsementsByProfileId(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody,
                                                                @PathVariable long profileId){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return endorsementService.getAllByProfileId(profileId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
