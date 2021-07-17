package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.utils.DateConverter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Service
public class MessageRepository extends BaseRepository<Message,Long>  {

    public MessageRepository() throws SQLException {
        super(Message.class);
    }



    @Override
    public void save(Message object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (chatId, senderId, body, isUnread, createdDate) VALUES (?, ?, ?, ?, ?)");
        ps.setLong(1, object.getChatId());
        ps.setLong(2, object.getSenderId());
        ps.setString(3, object.getBody());
        ps.setBoolean(4, object.getIsUnread());
        ps.setString(5, DateConverter.convertDate(object.getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));
        ps.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "chatId bigint not null," +
                "senderId bigint not null," +
                "body TEXT not null," +
                "isUnread bit not null," +
                "createdDate timestamp not null," +
                "foreign key (chatId) references " +  BaseEntity.getTableName(Chat.class) + "(id),"+
                "foreign key (senderId) references " +  BaseEntity.getTableName(Profile.class) + "(id)"+
            ")"
        );

        ps.execute();
    }



    @Override
    public Message convertSql(ResultSet resultSet) {
        Message message = new Message();
        try {
            resultSet.first();
            message.setId(resultSet.getLong("id"));
            message.setChatId(resultSet.getLong("chatId"));
            message.setSenderId(resultSet.getLong("senderId"));
            message.setBody(resultSet.getString("body"));
            message.setIsUnread(resultSet.getBoolean("isUnread"));
            message.setCreatedDate(DateConverter.parse(resultSet.getString("createdDate"), "yyyy-mm-dd hh:mm:ss"));
        } catch (SQLException | ParseException s){
            System.out.println(s.getMessage());
        }
        return message;
    }


    public ArrayList<Message> convertAllSql(ResultSet resultSet) throws SQLException, ParseException {
        ArrayList<Message> result = new ArrayList<>();
        while (resultSet.next()){
            Message message = new Message();
            message.setId(resultSet.getLong("id"));
            message.setChatId(resultSet.getLong("chatId"));
            message.setSenderId(resultSet.getLong("senderId"));
            message.setBody(resultSet.getString("body"));
            message.setIsUnread(resultSet.getBoolean("isUnread"));
            message.setCreatedDate(DateConverter.parse(resultSet.getString("createdDate"), "yyyy-mm-dd hh:mm:ss"));
            result.add(message);
        }
        return result;
    }


    public ArrayList<Message> getMessagesByChatId(long chatId) throws SQLException, ParseException {
        PreparedStatement retrievePs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE chatId=?");
        retrievePs.setLong(1, chatId);
        ResultSet resultSet = retrievePs.executeQuery();
        return this.convertAllSql(resultSet);
    }

}
