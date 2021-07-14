package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.repository.model.ProfileRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import org.springframework.stereotype.Service;

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

    public ChatRepository(ProfileService profileService) throws SQLException {
        super(Chat.class);
        this.profileService = profileService;
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
    public Chat convertSql(ResultSet resultSet) {
        Chat chat = new Chat();
        try {
            resultSet.next();
            chat.setId(resultSet.getLong("id"));
            chat.setProfileId1(resultSet.getLong("profileId1"));
            chat.setProfileId2(resultSet.getLong("profileId2"));
            chat.setIsArchive(resultSet.getBoolean("isArchive"));
            chat.setMarkUnread(resultSet.getBoolean("markUnread"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }
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

    public void setArchive(long chatId, boolean status) throws SQLException {
        PreparedStatement setArchivePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET isArchive=? WHERE id=?");
        setArchivePs.setBoolean(1, status);
        setArchivePs.setLong(2, chatId);
        setArchivePs.executeUpdate();
    }

    public void setUnread(long chatId, boolean status) throws SQLException {
        PreparedStatement setUnreadPs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET markUnread=? WHERE id=?");
        setUnreadPs.setBoolean(1, status);
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

    public ArrayList<Chat> findByArchived(long profileId, boolean status) throws SQLException {
        PreparedStatement findByArchived = this.conn.prepareStatement("SELECT * FROM "+this.tableName+
                " WHERE isArchive=? AND (profileId1=? OR profileId2=?)");
        findByArchived.setBoolean(1, status);
        findByArchived.setLong(2, profileId);
        findByArchived.setLong(3, profileId);
        ResultSet resultSet = findByArchived.executeQuery();
        return this.convertAllSql(resultSet);
    }

    public ArrayList<Chat> searchUser(String searchKey, long id) throws SQLException, ParseException {
        ArrayList<Chat> result = new ArrayList<>();
        long userId = profileService.findByUsername(searchKey).getId();
        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName
                +" WHERE (profileId1=? AND profileId2=?) OR (profileId1=? AND profileId2=?)"
        );

        searchPs.setLong(1, userId);
        searchPs.setLong(2, id);
        searchPs.setLong(3, id);
        searchPs.setLong(4, userId);
        ResultSet userResultSet = searchPs.executeQuery();
        result.add(this.convertSql(userResultSet));

        return result;
    }

    public HashMap<Chat, Message> searchMessages(String searchKey, long id) throws SQLException, ParseException {
        HashMap<Chat, Message> result = new HashMap<>();
        PreparedStatement searchPs = this.conn.prepareStatement("SELECT * FROM "+ this.tableName +" WHERE profileId1=? OR profileId2=?" +
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
            ArrayList<Message> messages = new MessageRepository().convertAllSql(resultSet);
            for (Message m:messages
            ) {
                if(m.getBody().contains(searchKey)){
                    result.put(c, m);
                }
            }
        }
        return result;
    }
}
