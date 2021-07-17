package com.project.linkedindatabase.service.model.chat;

import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.jsonToPojo.MessageJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface MessageService extends BaseService<Message,Long> {
    public List<Message> getMessagesByChatId(long chatId) throws SQLException, ParseException;
    public List<MessageJson> getAllMessageByChatIdJson(Long chatId) throws SQLException;
}
