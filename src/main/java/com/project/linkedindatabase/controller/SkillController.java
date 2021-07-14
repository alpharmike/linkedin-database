package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class SkillController {
    private final SkillService skillService;
    private final ProfileService profileService;

    public SkillController(SkillService skillService, ProfileService profileService) {
        this.skillService = skillService;
        this.profileService = profileService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/skill")
    public Skill getSkill(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody) throws SQLException {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            long id = (long) jsonBody.get("id");
            return skillService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/skill")
    public void postSkill(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody) throws SQLException {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Skill skill = new Skill();
            skill.setName(jsonBody.get("name"));
            skill.setProfileId(jsonBody.get("profileId"));
            skillService.save(skill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/skill")
    public void putSkill(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try{
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Skill skill = new Skill();
            skill.setName(jsonBody.get("name"));
            skill.setProfileId((long) jsonBody.get("profileId"));
            skillService.update(skill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/skill")
    public void delete(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Map<String, Object> jsonBody){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            skillService.deleteById((long) jsonBody.get("id"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
