package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MessageRepository extends BaseRepository<Message,Long> implements MessageService {

    public MessageRepository() throws SQLException {
        super(Message.class);
    }

    @Override
    public Message findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Message> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Message object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Message object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Message convertSql(ResultSet resultSet) {
        return null;
    }

}
