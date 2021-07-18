package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.skill.Skill;
import com.project.linkedindatabase.jsonToPojo.SkillPoJo;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.skill.EndorsementService;
import com.project.linkedindatabase.service.model.skill.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class SkillController {
    private final SkillService skillService;
    private final ProfileService profileService;
    private final EndorsementService endorsementService;


    public SkillController(SkillService skillService, ProfileService profileService, EndorsementService endorsementService) {
        this.skillService = skillService;
        this.profileService = profileService;
        this.endorsementService = endorsementService;
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
            skill.setName((String) jsonBody.get("name"));
            skill.setProfileId(profile.getId());
            skillService.save(skill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/skill/add-all")
    public void saveMultipleSkill(@RequestHeader Map<String, Object> jsonHeader, @RequestBody List<String> skills) throws SQLException {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            skillService.saveMultipleSkill(skills,profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @CrossOrigin(origins = "*")
    @PutMapping("/skill/{id}")
    public void putSkill(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Skill skill,
                            @PathVariable long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try{
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            skill.setId(id);
            skill.setProfileId(profile.getId());
            skillService.update(skill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/skill/{id}")
    public void delete(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Skill skill = skillService.findById(id);
            if (profile.getId().longValue() == skill.getProfileId().longValue()) {
                skillService.deleteById(id);
                // endorsementService.deleteAllBySkillId(id);
            }


        }catch (Exception e) {

            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/skills")
    public List<Skill> getSkills(@RequestHeader Map<String, Object> jsonHeader){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return skillService.getAllSkillByProfile(profile.getId());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/skills/{id}")
    public List<Skill> getSkillsByProfileId(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return skillService.getAllSkillByProfile(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/skills-json")
    public List<SkillPoJo> getSkillsJson(@RequestHeader Map<String, Object> jsonHeader){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return skillService.getAllSkillByProfileJson(profile.getId());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/skills-json/{id}")
    public List<SkillPoJo> getSkillsJsonByProfileId(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return skillService.getAllSkillByProfileJson(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
