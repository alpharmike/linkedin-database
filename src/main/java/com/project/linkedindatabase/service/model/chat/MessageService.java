package com.project.linkedindatabase.service.model.chat;

import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface MessageService extends BaseService<Message,Long> {
    public ArrayList<Message> getMessagesByChatId(long chatId) throws SQLException, ParseException;
}
