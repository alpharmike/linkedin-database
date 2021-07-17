package com.project.linkedindatabase.service.modelMap.chat;

import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.repository.model.chat.ChatRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.ChatService;
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

    public ChatServiceMap(ProfileService profileService) throws SQLException {
        this.chatRepository = new ChatRepository(profileService);
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
    public ArrayList<Chat> searchUser(String searchKey, long id) throws SQLException, ParseException {
        return chatRepository.searchUser(searchKey, id);
    }

    @Override
    public ArrayList<Chat> findByArchived(long profileId, boolean status) throws SQLException {
        return chatRepository.findByArchived(profileId, status);
    }

    @Override
    public ArrayList<Chat> findByUnread(long profileId, boolean status) throws SQLException {
        return chatRepository.findByUnread(profileId, status);
    }

    @Override
    public void setArchive(long chatId, boolean status) throws SQLException {
        chatRepository.setArchive(chatId, status);
    }

    @Override
    public void setUnread(long chatId, boolean status) throws SQLException {
        chatRepository.setUnread(chatId, status);
    }

    @Override
    public void delete(long chatId) throws SQLException {
        chatRepository.delete(chatId);
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
}
