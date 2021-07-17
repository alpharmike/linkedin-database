package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;
    private final ProfileService profileService;

    public MessageController(MessageService messageService, ProfileService profileService) {
        this.messageService = messageService;
        this.profileService = profileService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/message")
    public void save(@RequestHeader Map<String, Object> jsonHeader, @RequestBody Message message){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            message.setSenderId(profile.getId());

            message.setIsUnread(true);
            message.setCreatedDate(Calendar.getInstance());
            messageService.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }


}
