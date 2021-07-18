package com.project.linkedindatabase.service.model.chat;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.jsonToPojo.ChatJson;
import com.project.linkedindatabase.jsonToPojo.MessageJson;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ChatService extends BaseService<Chat,Long> {
    public ArrayList<Chat> searchUser(String searchKey, long id) throws SQLException, ParseException;
    public ArrayList<Chat> findByArchived(long profileId, boolean status) throws SQLException;
    public ArrayList<ChatJson> findByArchivedJson(long profileId, boolean status) throws SQLException;
    public ArrayList<Chat> findByUnread(long profileId, boolean status) throws SQLException;
    public ArrayList<ChatJson> findByUnreadJson(long profileId, boolean status) throws SQLException;
    public void setArchive(long chatId) throws SQLException;
    public void setUnread(long chatId) throws SQLException;
    public void delete(long chatId) throws SQLException;
    public void deleteChatCompletelyById(long chatId) throws SQLException;
    public boolean exists(long profileId1, long profileId2) throws SQLException;
    public Chat getChatByProfileId(long profileId1, long profileId2) throws SQLException;
    public ArrayList<Chat> convertAllSql(ResultSet resultSet) throws SQLException;
    public HashMap<Chat, Message> searchMessages(String searchKey, long id) throws SQLException, ParseException;
    public Chat findByProfileIds(long profileId1, long profileId2) throws SQLException;
    public boolean isThereChat(Long profileId1,Long profileId2) throws SQLException;

    public List<Chat> getAllChatByProfileId(Long profileId) throws SQLException ;
    public List<ChatJson> getAllChatByProfileIdJson(Long profileId) throws SQLException ;

    public ChatJson getChatByChatId(Long chatId,Long userIdRequest) throws SQLException ;


}
