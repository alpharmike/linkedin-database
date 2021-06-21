package com.project.linkedindatabase.repository.model.chat;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.BackgroundService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ChatRepository extends BaseRepository<Chat,Long> implements ChatService {

    public ChatRepository() throws SQLException {
        super(Chat.class);
    }

    @Override
    public Chat findById(Long id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public List<Chat> findAll() throws SQLException {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        super.deleteById(id);
    }

    @Override
    public void save(Chat object) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void deleteByObject(Chat object) throws SQLException {
        super.deleteByObject(object);

    }

    @Override
    public Chat convertSql(ResultSet resultSet) {
        return null;
    }

}
