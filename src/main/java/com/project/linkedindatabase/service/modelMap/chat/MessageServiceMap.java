package com.project.linkedindatabase.service.modelMap.chat;

import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.jsonToPojo.MessageJson;
import com.project.linkedindatabase.repository.model.chat.MessageRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.MessageService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MessageServiceMap implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceMap(ProfileService profileService) throws SQLException {
        this.messageRepository = new MessageRepository(profileService);
    }

    @Override
    public Message findById(Long aLong) throws SQLException {
        return messageRepository.findById(aLong);
    }

    @Override
    public void save(Message object) throws SQLException {
        messageRepository.save(object);
    }

    @Override
    public List<Message> findAll() throws SQLException {
        return messageRepository.findAll();
    }

    @Override
    public void deleteByObject(Message object) throws SQLException {
        messageRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        messageRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        messageRepository.createTable();
    }

    @Override
    public List<Message> getMessagesByChatId(long chatId) throws SQLException, ParseException {
        return messageRepository.getMessagesByChatId(chatId);
    }

    @Override
    public List<MessageJson> getAllMessageByChatIdJson(Long chatId) throws SQLException {
        return messageRepository.getAllMessageByChatIdJson(chatId);
    }
    @Override
    public MessageJson convertToMessageJson(Message message) throws SQLException
    {
        return messageRepository.convertToMessageJson(message);
    }
}
