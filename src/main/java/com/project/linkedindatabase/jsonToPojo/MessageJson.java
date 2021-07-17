package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.*;

import java.util.Calendar;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageJson {

    private Long id;
    private String body;

    private Boolean isUnread;

    private String createdDate;

    private Long chatId;// foreign key to chat table

    private Long senderId;
    private ProfileJson profileJson;

    public static MessageJson convertToJson(Message message)
    {
        MessageJson messageJson = new MessageJson();
        messageJson.setId(message.getId());
        messageJson.setBody(message.getBody());
        messageJson.setChatId(message.getChatId());
        messageJson.setCreatedDate( DateConverter.convertDate(message.getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));
        messageJson.setSenderId(message.getSenderId());
        messageJson.setIsUnread(message.getIsUnread());

        return messageJson;
    }

    @SneakyThrows
    public Message convertToMessage()
    {
        Message message = new Message();
        message.setId(getId());
        message.setBody(getBody());
        message.setChatId(getChatId());
        message.setCreatedDate( DateConverter.parse(getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));
        message.setSenderId(getSenderId());
        message.setIsUnread(getIsUnread());
        return message;

    }



}
