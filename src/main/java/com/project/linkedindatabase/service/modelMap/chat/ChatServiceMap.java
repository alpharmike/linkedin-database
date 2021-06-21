package com.project.linkedindatabase.service.modelMap.chat;

import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.repository.model.chat.ChatRepository;
import com.project.linkedindatabase.service.model.chat.ChatService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ChatServiceMap implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceMap() throws SQLException {
        this.chatRepository = new ChatRepository();
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
}
