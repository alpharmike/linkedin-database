package com.project.linkedindatabase.jsonToPojo;


import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.domain.chat.Message;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatJson {

    private Long id;
    private Boolean isArchive;

    private Boolean markUnread;

    private Long myProfileId;// foreign key to profile table.
    private ProfileJson myProfile;
    private Long otherProfileId;// foreign key to profile table.
    private ProfileJson otherProfile;

    private List<MessageJson> messageJsons = new ArrayList<>();

    public static ChatJson convertToJson(Chat chat)
    {
        ChatJson chatJson = new ChatJson();
        chatJson.setId(chat.getId());
        chatJson.setIsArchive(chat.getIsArchive());
        chatJson.setMarkUnread(chat.getMarkUnread());

        chatJson.setMyProfileId(chat.getProfileId1());
        chatJson.setOtherProfileId(chat.getProfileId2());

        return chatJson;
    }


    public Chat convertToMessage()
    {
        Chat chat = new Chat();
        chat.setId(getId());
        chat.setIsArchive(getIsArchive());
        chat.setMarkUnread(getMarkUnread());

        chat.setProfileId1(getMyProfileId());
        chat.setProfileId2(getOtherProfileId());
        return chat;

    }

}
