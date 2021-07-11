package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ChatRepository extends BaseRepository<Chat,Long> {

    public ChatRepository() throws SQLException {
        super(Chat.class);
    }


    @Override
    public void save(Chat object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileId1, profileId2, isArchive, markUnread) VALUES (?, ?, ?, ?)");
        ps.setLong(1, object.getProfileId1());
        ps.setLong(2, object.getProfileId2());
        ps.setBoolean(3, object.getIsArchive());
        ps.setBoolean(4, object.getMarkUnread());
        ps.executeQuery();
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
            resultSet.first();
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

}
