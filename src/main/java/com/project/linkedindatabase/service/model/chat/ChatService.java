package com.project.linkedindatabase.service.model.chat;

import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.service.BaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ChatService extends BaseService<Chat,Long> {
    public ArrayList<Chat> searchUser(String searchKey, long id) throws SQLException, ParseException;
    public ArrayList<Chat> findByArchived(long profileId, boolean status) throws SQLException;
    public ArrayList<Chat> findByUnread(long profileId, boolean status) throws SQLException;
    public void setArchive(long chatId, boolean status) throws SQLException;
    public void setUnread(long chatId, boolean status) throws SQLException;
    public void delete(long chatId) throws SQLException;
    public boolean exists(long profileId1, long profileId2) throws SQLException;
    public Chat getChatByProfileId(long profileId1, long profileId2) throws SQLException;
    public ArrayList<Chat> convertAllSql(ResultSet resultSet) throws SQLException;
    public HashMap<Chat, Message> searchMessages(String searchKey, long id) throws SQLException, ParseException;
    public Chat findByProfileIds(long profileId1, long profileId2) throws SQLException;
}
