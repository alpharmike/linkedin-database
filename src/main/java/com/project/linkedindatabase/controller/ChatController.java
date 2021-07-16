package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatService chatService;
    private final ProfileService profileService;
    private final MessageService messageService;

    public ChatController(ChatService chatService, ProfileService profileService, MessageService messageService) {
        this.chatService = chatService;
        this.profileService = profileService;
        this.messageService = messageService;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/chat/{id1}-{id2}")
    public void createChat(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id1, @PathVariable long id2){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Chat chat = new Chat();
            chat.setProfileId1(id1);
            chat.setProfileId2(id2);
            chat.setIsArchive(false);
            chat.setMarkUnread(false);
            chatService.save(chat);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/chat/{id1}-{id2}")
    public void deleteChat(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id1, @PathVariable long id2) throws SQLException {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Chat chat = chatService.findByProfileIds(id1, id2);
            chatService.deleteByObject(chat);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/chat-unread-{status}/{id}")
    public void setMarkAsUnread(@RequestHeader Map<String, Object> jsonHeader, @PathVariable boolean status, @PathVariable long id){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            chatService.setUnread(id, status);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chat-messages/{chatId}")
    public ArrayList<Message> getMessages(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long chatId){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return messageService.getMessagesByChatId(chatId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/chats/is_archive={status}")
    public ArrayList<Chat> getChatsByArchive(@RequestHeader Map<String, Object> jsonHeader, @PathVariable boolean status){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.findByArchived(profile.getId(), status);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/is_unread={status}")
    public ArrayList<Chat> getChatsByUnread(@RequestHeader Map<String, Object> jsonHeader, @PathVariable boolean status){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.findByUnread(profile.getId(), status);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/search-user/{searchKey}")
    public ArrayList<Chat> search(@RequestHeader Map<String, Object> jsonHeader, @PathVariable String searchKey){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.searchUser(searchKey, profile.getId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /*@CrossOrigin(origins = "*")
    @GetMapping("/chats/search-messages/{searchKey}")
    public ArrayList<Chat> search(@RequestHeader Map<String, Object> jsonHeader, @PathVariable String searchKey){
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.searchMessages(searchKey, profile.getId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/
}
