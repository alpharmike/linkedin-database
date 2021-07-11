package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class MessageRepository extends BaseRepository<Message,Long>  {

    public MessageRepository() throws SQLException {
        super(Message.class);
    }



    @Override
    public void save(Message object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (chatId, senderId, text, isUnread, createdDate) VALUES (?, ?, ?, ?, ?)");
        ps.setLong(1, object.getChatId());
        ps.setLong(2, object.getSenderId());
        ps.setString(3, object.getText());
        ps.setBoolean(4, object.getIsUnread());
        ps.setTimestamp(5, object.getCreatedDate());
        ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "chatId bigint not null," +
                "senderId bigint not null," +
                "text TEXT not null," +
                "isUnread bit not null," +
                "createdDate timestamp not null," +
                "foreign key (chatId) references chat(id)," +
                "foreign key (senderId) references profile(id)" +
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
            message.setText(resultSet.getString("text"));
            message.setIsUnread(resultSet.getBoolean("isUnread"));
            message.setCreatedDate(resultSet.getTimestamp("createdDate"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return message;
    }

}
