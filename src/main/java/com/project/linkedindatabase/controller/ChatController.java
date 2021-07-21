package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.jsonToPojo.ChatJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void createChat(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id1,
                           @PathVariable long id2) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Chat chat = new Chat();
            chat.setProfileId1(id1);
            chat.setProfileId2(id2);
            chat.setIsArchive(false);
            chat.setMarkUnread(false);
            if (!chatService.isThereChat(id1, id2)) chatService.save(chat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/chat-token/{id}")
    public Chat createChatById(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        if (profile.getId() == id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "both id is the same ", new Exception("same"));
        }

        boolean isThereChat = false;
        try {

            if (chatService.isThereChat(id, profile.getId())) isThereChat = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


        try {

            if (!isThereChat) {
                Chat chat = new Chat();
                chat.setProfileId1(profile.getId());
                chat.setProfileId2(id);
                chat.setIsArchive(false);
                chat.setMarkUnread(false);
                chatService.save(chat);
            }

            return chatService.findByProfileIds(id, profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/chat/{id1}-{id2}")
    public void deleteChat(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id1,
                           @PathVariable long id2) throws SQLException {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Chat chat = chatService.findByProfileIds(id1, id2);
            if (chat != null) chatService.deleteByObject(chat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/chat/{id}")
    public Chat deleteChatById(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id) throws SQLException {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            chatService.deleteChatCompletelyById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with token ", e);

        }
        try {
            return chatService.findByProfileIds(id, profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with data ", e);

        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/chat-unread/{id}")
    public void toggleUnreadStatus(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            chatService.setUnread(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/chat-archive/{id}")
    public void toggleArchiveStatus(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);

            chatService.setArchive(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chat-messages/{chatId}")
    public List<Message> getMessages(@RequestHeader Map<String, Object> jsonHeader, @PathVariable long chatId) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return messageService.getMessagesByChatId(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/chats/archived")
    public ArrayList<ChatJson> getChatsByArchive(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.findByArchivedJson(profile.getId(), true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/unread")
    public ArrayList<ChatJson> getChatsByUnread(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.findByUnreadJson(profile.getId(), true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/search-user/{searchKey}")
    public ArrayList<Chat> searchForUser(@RequestHeader Map<String, Object> jsonHeader,
                                         @PathVariable String searchKey) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.searchUser(searchKey, profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/search-user-json/{searchKey}")
    public List<ChatJson> searchForUserJson(@RequestHeader Map<String, Object> jsonHeader,
                                         @PathVariable String searchKey) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.searchUserJson(searchKey, profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/search-messages/{searchKey}")
    public HashMap<Chat, Message> searchForMessages(@RequestHeader Map<String, Object> jsonHeader,
                                                    @PathVariable String searchKey) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.searchMessages(searchKey, profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chats/search-messages-json/{searchKey}")
    public List<ChatJson> searchForMessagesJson(@RequestHeader Map<String, Object> jsonHeader,
                                                    @PathVariable String searchKey) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.searchMessagesJson(searchKey, profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/chats-json")
    public List<ChatJson> getAllChatByToken(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.getAllChatByProfileIdJson(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with token ", e);

        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/chat-json/{id}")
    public ChatJson getChatById(@RequestHeader Map<String, Object> jsonHeader, @PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Profile profile;
        try {
            profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return chatService.getChatByChatId(id,profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a problem with token ", e);

        }
    }
}
