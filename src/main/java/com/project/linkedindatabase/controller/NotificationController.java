package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Notification;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.NotificationJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.NotificationService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;
    private final ProfileService profileService;

    public NotificationController(NotificationService notificationService, ProfileService profileService) {
        this.notificationService = notificationService;
        this.profileService = profileService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/notification")
    public ArrayList<NotificationJson> getNotificationsByTargetProfileId(@RequestHeader Map<String, Object> jsonHeader) throws Exception {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return notificationService.getAllNotificationsByTargetProfileId(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/notification/birthday")
    public void sendAllBirthdayNotifications() throws SQLException {
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String today = dtf.format(zonedDateTime);
        System.out.println(today);
        for (Profile p : profileService.findAll()) {
            System.out.println(p.getUsername());
            System.out.println(p.getDateOfBirth().get(Calendar.DAY_OF_YEAR));
            System.out.println(DateConverter.getToday().get(Calendar.DAY_OF_YEAR));
            if (p.getDateOfBirth().get(Calendar.DAY_OF_YEAR) == DateConverter.getToday().get(Calendar.DAY_OF_YEAR)) {
                try {
                    notificationService.saveBirthdayNotification(p.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
