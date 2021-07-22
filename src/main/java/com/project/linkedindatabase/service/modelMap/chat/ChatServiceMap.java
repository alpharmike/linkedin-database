package com.project.linkedindatabase.service.modelMap.chat;

import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.jsonToPojo.ChatJson;
import com.project.linkedindatabase.repository.model.chat.ChatRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChatServiceMap implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceMap(ProfileService profileService, MessageService messageService) throws SQLException {
        this.chatRepository = new ChatRepository(profileService, messageService);
    }


    @Override
    public Chat findById(Long aLong) throws SQLException {
        return chatRepository.findById(aLong);
    }

    @Override
    public void save(Chat object) throws SQLException {
        chatRepository.save(object);
    }

    @Override
    public List<Chat> findAll() throws SQLException {
        return chatRepository.findAll();
    }

    @Override
    public void deleteByObject(Chat object) throws SQLException {
        chatRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        chatRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        chatRepository.createTable();
    }

    @Override
    public ArrayList<Chat> searchUser(String searchKey, long id) throws Exception {
        return chatRepository.searchUser(searchKey, id);
    }

    @Override
    public ArrayList<Chat> findByArchived(long profileId, boolean status) throws SQLException {
        return chatRepository.findByArchived(profileId, status);
    }

    @Override
    public ArrayList<ChatJson> findByArchivedJson(long profileId, boolean status) throws SQLException {
        return chatRepository.findByArchivedJson(profileId, status);
    }

    @Override
    public ArrayList<Chat> findByUnread(long profileId, boolean status) throws SQLException {
        return chatRepository.findByUnread(profileId, status);
    }

    @Override
    public ArrayList<ChatJson> findByUnreadJson(long profileId, boolean status) throws SQLException {
        return chatRepository.findByUnreadJson(profileId, status);
    }

    @Override
    public void setArchive(long chatId) throws SQLException {
        chatRepository.setArchive(chatId);
    }

    @Override
    public void setUnread(long chatId) throws SQLException {
        chatRepository.setUnread(chatId);
    }

    @Override
    public void delete(long chatId) throws SQLException {
        chatRepository.delete(chatId);
    }

    @Override
    public void deleteChatCompletelyById(long chatId) throws SQLException {
        chatRepository.deleteChatCompletelyById(chatId);
    }

    @Override
    public boolean exists(long profileId1, long profileId2) throws SQLException {
        return chatRepository.exists(profileId1, profileId2);
    }

    @Override
    public Chat getChatByProfileId(long profileId1, long profileId2) throws SQLException {
       return chatRepository.getChatByProfileId(profileId1, profileId2);
    }

    @Override
    public ArrayList<Chat> convertAllSql(ResultSet resultSet) throws SQLException {
        return chatRepository.convertAllSql(resultSet);
    }

    @Override
    public HashMap<Chat, Message> searchMessages(String searchKey, long id) throws SQLException, ParseException {
        return chatRepository.searchMessages(searchKey, id);
    }

    @Override
    public Chat findByProfileIds(long profileId1, long profileId2) throws SQLException {
        return chatRepository.findByProfileIds(profileId1, profileId2);
    }

    @Override
    public boolean isThereChat(Long profileId1, Long profileId2) throws SQLException {
        return chatRepository.isThereChat(profileId1,profileId2);
    }

    @Override
    public List<Chat> getAllChatByProfileId(Long profileId) throws SQLException {
        return chatRepository.getAllChatByProfileId(profileId);
    }

    @Override
    public List<ChatJson> getAllChatByProfileIdJson(Long profileId) throws SQLException {
        return chatRepository.getAllChatByProfileIdJson(profileId);
    }

    @Override
    public List<ChatJson> searchUserJson(String searchKey, long id) throws Exception{
        return chatRepository.searchUserJson(searchKey,id);
    }

    @Override
    public List<ChatJson> searchUserUnreadJson(String searchKey, long id,Boolean isUnread) throws Exception {
        return chatRepository.searchUserUnreadJson(searchKey,id,isUnread);
    }

    @Override
    public List<ChatJson> searchUserArchiverJson(String searchKey, long id,Boolean isArchive) throws Exception {
        return chatRepository.searchUserArchiverJson(searchKey,id,isArchive);
    }

    @Override
    public ChatJson getChatByChatId(Long chatId,Long userIdRequest) throws SQLException {
        return chatRepository.getChatByChatId(chatId,userIdRequest);
    }

    @Override
    public List<ChatJson> searchMessagesJson(String searchKey, long id) throws SQLException, ParseException {
        return chatRepository.searchMessagesJson(searchKey,id);
    }

    @Override
    public ChatJson searchMessagesBaseChatIdJson(String searchKey, long id, long chatId) throws SQLException, ParseException {
        return chatRepository.searchMessagesBaseChatIdJson(searchKey,id,chatId);
    }
}
