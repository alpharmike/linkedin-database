package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.jsonToPojo.ChatJson;
import com.project.linkedindatabase.jsonToPojo.MessageJson;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChatRepository extends BaseRepository<Chat,Long> {

    private final ProfileService profileService;
    private final MessageService messageService;

    public ChatRepository(ProfileService profileService, MessageService messageService) throws SQLException {
        super(Chat.class);
        this.profileService = profileService;
        this.messageService = messageService;
    }


    @Override
    public void save(Chat object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileId1, profileId2, isArchive, markUnread) VALUES (?, ?, ?, ?)");
        ps.setLong(1, object.getProfileId1());
        ps.setLong(2, object.getProfileId2());
        ps.setBoolean(3, object.getIsArchive());
        ps.setBoolean(4, object.getMarkUnread());
        ps.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileId1 bigint not null," +
                "profileId2 bigint not null," +
                "isArchive bit not null," +
                "markUnread bit not null," +
                "foreign key (profileId1) references " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "foreign key (profileId2) references " +  BaseEntity.getTableName(Profile.class) + "(id)"+
            ")"
        );

        ps.execute();
    }



    @Override
    public Chat convertSql(ResultSet resultSet) throws SQLException {
        Chat chat = new Chat();


        chat.setId(resultSet.getLong("id"));
        chat.setProfileId1(resultSet.getLong("profileId1"));
        chat.setProfileId2(resultSet.getLong("profileId2"));
        chat.setIsArchive(resultSet.getBoolean("isArchive"));
        chat.setMarkUnread(resultSet.getBoolean("markUnread"));



        return chat;
    }

    public ArrayList<Chat> convertAllSql(ResultSet resultSet) throws SQLException {
        ArrayList<Chat> result = new ArrayList<>();
        while (resultSet.next()){
            Chat chat = new Chat();
            chat.setId(resultSet.getLong("id"));
            chat.setProfileId1(resultSet.getLong("profileId1"));
            chat.setProfileId2(resultSet.getLong("profileId2"));
            chat.setIsArchive(resultSet.getBoolean("isArchive"));
            chat.setMarkUnread(resultSet.getBoolean("markUnread"));
            result.add(chat);
        }
        return result;
    }
    public Chat getChatByProfileId(long profileId1, long profileId2) throws SQLException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE (profileId1=? AND profileId2=?) OR " +
                "(profileId1=? AND profileId2=?)");
        retrievePs.setLong(1, profileId1);
        retrievePs.setLong(2, profileId2);
        retrievePs.setLong(3, profileId2);
        retrievePs.setLong(4, profileId1);
        ResultSet resultSet = retrievePs.executeQuery();
        if(!resultSet.next()){
            return null;
        }
        return this.convertSql(resultSet);
    }
    public boolean exists(long profileId1, long profileId2) throws SQLException {
        return getChatByProfileId(profileId1, profileId2) != null;
    }

    public void setArchive(long chatId) throws SQLException {
        PreparedStatement setArchivePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET isArchive=? WHERE id=?");
        Chat chat = findById(chatId);
        setArchivePs.setBoolean(1, !chat.getIsArchive());
        setArchivePs.setLong(2, chatId);
        setArchivePs.executeUpdate();
    }

    public void setUnread(long chatId) throws SQLException {
        PreparedStatement setUnreadPs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET markUnread=? WHERE id=?");
        Chat chat = findById(chatId);
        setUnreadPs.setBoolean(1, !chat.getMarkUnread());
        setUnreadPs.setLong(2, chatId);
        setUnreadPs.executeUpdate();
    }

    public void delete(long chatId) throws SQLException {
        PreparedStatement deletePs = this.conn.prepareStatement("DELETE FROM "+this.tableName+" WHERE id=?");
        deletePs.setLong(1, chatId);
        deletePs.execute();
    }

    public ArrayList<Chat> findByUnread(long profileId, boolean status) throws SQLException {
        PreparedStatement findByUnreadPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+
                " WHERE markUnread=? AND (profileId1=? OR profileId2=?)");
        findByUnreadPs.setBoolean(1, status);
        findByUnreadPs.setLong(2, profileId);
        findByUnreadPs.setLong(3, profileId);
        ResultSet resultSet = findByUnreadPs.executeQuery();
        return this.convertAllSql(resultSet);
    }

    public ArrayList<ChatJson> findByUnreadJson(long profileId, boolean status) throws SQLException {
        ArrayList<Chat> unreadChats = findByUnread(profileId, status);
        ArrayList<ChatJson> chatJsons = new ArrayList<>();
        for (Chat i: unreadChats)
        {
            chatJsons.add(convertToJson(i,profileId));
        }
        return chatJsons;
    }

    public ArrayList<Chat> findByArchived(long profileId, boolean status) throws SQLException {
        PreparedStatement findByArchived = this.conn.prepareStatement("SELECT * FROM "+this.tableName+
                " WHERE isArchive=? AND (profileId1=? OR profileId2=?)");
        findByArchived.setBoolean(1, status);
        findByArchived.setLong(2, profileId);
        findByArchived.setLong(3, profileId);
        ResultSet resultSet = findByArchived.executeQuery();
        return this.convertAllSql(resultSet);
    }

    public ArrayList<ChatJson> findByArchivedJson(long profileId, boolean status) throws SQLException {
        ArrayList<Chat> archivedChats = findByArchived(profileId, status);
        ArrayList<ChatJson> chatJsons = new ArrayList<>();
        for (Chat i: archivedChats)
        {
            chatJsons.add(convertToJson(i,profileId));
        }
        return chatJsons;
    }

    public ArrayList<Chat> searchUser(String searchKey, long id) throws Exception
    {
        List<Profile> profiles = profileService.searchOtherBaseName(searchKey);
        ArrayList<Chat> chats = new ArrayList<>();
        for (Profile p :profiles)
        {
            chats.addAll(searchUserProfile(p,id));
        }

        return chats;
    }




    public ArrayList<Chat> searchUserProfile(Profile profile, long id) throws SQLException, ParseException {
        ArrayList<Chat> result = new ArrayList<>();

        long userId = profile.getId();
        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName
                +" WHERE (profileId1=? AND profileId2=?) OR (profileId1=? AND profileId2=?)"
        );

        searchPs.setLong(1, userId);
        searchPs.setLong(2, id);
        searchPs.setLong(3, id);
        searchPs.setLong(4, userId);
        ResultSet userResultSet = searchPs.executeQuery();
        result = this.convertAllSql(userResultSet);

        return result;
    }
    public List<ChatJson> searchUserJson(String searchKey, long id) throws Exception {
        ArrayList<Chat> chats = searchUser(searchKey,id);


        List<ChatJson> chatJsonList = new ArrayList<>();
        for (Chat i : chats)
            chatJsonList.add(convertToJson(i,id));
        return chatJsonList;
    }
    public List<ChatJson> searchUserArchiverJson(String searchKey, long id,Boolean isArchive) throws Exception {

        List<Profile> profiles = profileService.searchOtherBaseName(searchKey);
        List<Chat> chats = new ArrayList<>();
        for (Profile p:profiles)
        {
            chats.addAll(searchUserArchiverOne(p,id,isArchive));
        }
        List<ChatJson> chatJsonList = new ArrayList<>();
        for (Chat c: chats)
        {
            chatJsonList.add(convertToJson(c,id));
        }

        return chatJsonList;
    }
    public List<Chat> searchUserArchiverOne(Profile profile, long id,Boolean isUnread) throws Exception {
        List<Chat> chats = new ArrayList<>();

        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName
                +" WHERE ((profileId1=? AND profileId2=?) OR (profileId1=? AND profileId2=?)) and isArchive = ? "
        );

        searchPs.setLong(1, profile.getId());
        searchPs.setLong(2, id);
        searchPs.setLong(3, id);
        searchPs.setLong(4, profile.getId());
        searchPs.setBoolean(5, isUnread);
        ResultSet userResultSet = searchPs.executeQuery();
        chats = this.convertAllSql(userResultSet);

        return chats;
    }

    public List<ChatJson> searchUserUnreadJson(String searchKey, long id,Boolean isUnread) throws Exception
    {
        List<Profile> profiles = profileService.searchOtherBaseName(searchKey);
        List<Chat> chats = new ArrayList<>();
        for (Profile p:profiles)
        {
            chats.addAll(searchUserIsUnreadOne(p,id,isUnread));
        }
        List<ChatJson> chatJsonList = new ArrayList<>();
        for (Chat c: chats)
        {
            chatJsonList.add(convertToJson(c,id));
        }

        return chatJsonList;
    }

    public List<Chat> searchUserIsUnreadOne(Profile profile, long id,Boolean isUnread) throws Exception {
        List<Chat> chats = new ArrayList<>();

        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName
                +" WHERE ((profileId1=? AND profileId2=?) OR (profileId1=? AND profileId2=?)) and markUnread = ? "
        );

        searchPs.setLong(1, profile.getId());
        searchPs.setLong(2, id);
        searchPs.setLong(3, id);
        searchPs.setLong(4, profile.getId());
        searchPs.setBoolean(5, isUnread);
        ResultSet userResultSet = searchPs.executeQuery();
        chats = this.convertAllSql(userResultSet);

        return chats;
    }


    public HashMap<Chat, Message> searchMessages(String searchKey, long id) throws SQLException, ParseException {
        HashMap<Chat, Message> result = new HashMap<>();
        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName +" WHERE (profileId1=? OR profileId2=?)" +
                " AND id IN (SELECT chatId FROM "+BaseEntity.getTableName(Message.class)+")");
        searchPs.setLong(1, id);
        searchPs.setLong(2, id);
        ResultSet messagesResultSet = searchPs.executeQuery();
        ArrayList<Chat> resultTemp = this.convertAllSql(messagesResultSet);
        for (Chat c:resultTemp
        ) {
            PreparedStatement searchMessagePs = this.conn.prepareStatement("SELECT * FROM "+BaseEntity.getTableName(Message.class)+
                    " WHERE chatId=?");
            searchMessagePs.setLong(1, c.getId());
            ResultSet resultSet = searchMessagePs.executeQuery();
            ArrayList<Message> messages = new MessageRepository(profileService).convertAllSql(resultSet);
            for (Message m:messages
            ) {
                if(m.getBody().contains(searchKey)){
                    result.put(c, m);
                }
            }
        }
        return result;
    }

    public List<ChatJson> searchMessagesJson(String searchKey, long id) throws SQLException, ParseException {
       List<ChatJson> result = new ArrayList<>();
        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName +" as ch WHERE (profileId1=? OR profileId2=?)" +
                " AND id IN (SELECT chatId FROM "+BaseEntity.getTableName(Message.class)+") and exists  " +
                "(select * from message as ms where ms.body like ? and ms.chatId = ch.id)");
        searchPs.setLong(1, id);
        searchPs.setLong(2, id);
        searchPs.setString(3, "%"+searchKey+"%");

        ResultSet messagesResultSet = searchPs.executeQuery();
        ArrayList<Chat> resultTemp = this.convertAllSql(messagesResultSet);

        for (Chat c:resultTemp
        ) {

            PreparedStatement searchMessagePs = this.conn.prepareStatement("SELECT * FROM "+BaseEntity.getTableName(Message.class)+
                    " WHERE chatId=? and body like ?");
            searchMessagePs.setLong(1, c.getId());
            searchMessagePs.setString(2, "%"+searchKey+"%");
            ResultSet resultSet = searchMessagePs.executeQuery();

            ArrayList<Message> messages = new MessageRepository(profileService).convertAllSql(resultSet);
            ChatJson chatJson = convertToJson(c,id);
            chatJson.setMessageJsons(new ArrayList<>());
            for (Message m:messages
            ) {

                if(m.getBody().contains(searchKey)){
                    chatJson.getMessageJsons().add(messageService.convertToMessageJson(m));

                }
                result.add(chatJson);
            }
        }
        return result;
    }

    public ChatJson searchMessagesBaseChatIdJson(String searchKey, long id, long chatId) throws SQLException, ParseException {
        Chat chat = findChatByChatIdAndProfileId(id,chatId);
        if (chat == null)
            return null;
        PreparedStatement searchMessagePs = this.conn.prepareStatement("SELECT * FROM "+BaseEntity.getTableName(Message.class)+
                " WHERE chatId = ? and body like ?");
        searchMessagePs.setLong(1, chat.getId());
        searchMessagePs.setString(2, "%"+searchKey+"%");
        ResultSet resultSet = searchMessagePs.executeQuery();

        ArrayList<Message> messages = new MessageRepository(profileService).convertAllSql(resultSet);
        ChatJson chatJson = convertToJson(chat,id);
        chatJson.setMessageJsons(new ArrayList<>());
        for (Message m:messages
        ) {

            if(m.getBody().contains(searchKey)){
                chatJson.getMessageJsons().add(messageService.convertToMessageJson(m));

            }

        }

        return chatJson;

    }

    public Chat findByProfileIds(long profileId1, long profileId2) throws SQLException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "(profileId1=? AND profileId2=?) OR  (profileId1=? AND profileId2=?);");
        retrievePs.setLong(1, profileId1);
        retrievePs.setLong(2, profileId2);
        retrievePs.setLong(3, profileId2);
        retrievePs.setLong(4, profileId1);

        ResultSet resultSet = retrievePs.executeQuery();

        if (!resultSet.isBeforeFirst())
        {

            return null;
        }
        resultSet.next();

        return this.convertSql(resultSet);
    }

    public void deleteChatCompletelyById(long chatId) throws SQLException {

        if (findById(chatId) == null) {
            return;
        }
        PreparedStatement deleteMsgPs = this.conn.prepareStatement("DELETE FROM "+BaseEntity.getTableName(Message.class)+" WHERE " +
                "chatId = ?");;

        deleteMsgPs.setLong(1, chatId);

        PreparedStatement deleteChatPs = this.conn.prepareStatement("DELETE FROM "+this.tableName+" WHERE " +
                "id = ?;");

        deleteChatPs.setLong(1, chatId);

        deleteMsgPs.execute();
        deleteChatPs.execute();

    }

    public boolean isThereChat(Long profileId1,Long profileId2) throws SQLException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "(profileId1=? AND profileId2=?) OR  (profileId1=? AND profileId2=?)");
        retrievePs.setLong(1, profileId1);
        retrievePs.setLong(2, profileId2);
        retrievePs.setLong(3, profileId2);
        retrievePs.setLong(4, profileId1);
        ResultSet resultSet = retrievePs.executeQuery();

        if (!resultSet.isBeforeFirst() ) {
            return false;
        }else
        {
            return true;
        }

    }


    public List<Chat> getAllChatByProfileId(Long profileId) throws SQLException {
        PreparedStatement findByArchived = this.conn.prepareStatement("SELECT * FROM "+this.tableName+
                " WHERE profileId1=? OR profileId2=?");

        findByArchived.setLong(1, profileId);
        findByArchived.setLong(2, profileId);
        ResultSet resultSet = findByArchived.executeQuery();
        return this.convertAllSql(resultSet);
    }
    public List<ChatJson> getAllChatByProfileIdJson(Long profileId) throws SQLException {
        List<Chat> chats = getAllChatByProfileId(profileId);
        List<ChatJson> chatJsons = new ArrayList<>();
        for (Chat i: chats)
        {
            chatJsons.add(convertToJson(i,profileId));
        }
        return chatJsons;

    }

    public ChatJson getChatByChatId(Long chatId,Long userIdRequest) throws SQLException {
        Chat chat = findById(chatId);
        if (chat != null)
            return convertToJson(chat,userIdRequest);
        return null;
    }

    public ChatJson convertToJson(Chat chat,Long userIdRequest) throws SQLException {
        ChatJson chatJson = ChatJson.convertToJson(chat);

        Profile profile1 = profileService.findById(chatJson.getMyProfileId());
        Profile profile2 = profileService.findById(chatJson.getOtherProfileId());
        List<MessageJson> messageJsons = messageService.getAllMessageByChatIdJson(chatJson.getId());

        if (profile1.getId() == userIdRequest) {
            chatJson.setMyProfile(ProfileJson.convertToJson(profile1));
            chatJson.setOtherProfile(ProfileJson.convertToJson(profile2));
        }else
        {
            chatJson.setMyProfile(ProfileJson.convertToJson(profile2));
            chatJson.setOtherProfile(ProfileJson.convertToJson(profile1));
        }

        chatJson.setMessageJsons(messageJsons);

        return chatJson;
    }

    public Chat findChatByChatIdAndProfileId(Long  profileId,Long chatId) throws SQLException
    {
        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName
                +" WHERE (profileId1=? or profileId2=?) AND  id = ?"
        );
        searchPs.setLong(1, profileId);
        searchPs.setLong(2, profileId);
        searchPs.setLong(3, chatId);
        ResultSet userResultSet = searchPs.executeQuery();
        ArrayList<Chat>result = this.convertAllSql(userResultSet);
        if (result.size() > 0)
            return result.get(0);
        return null;
    }

}
