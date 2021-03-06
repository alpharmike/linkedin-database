package com.project.linkedindatabase.domain.chat;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "message")
public class Message extends BaseEntity {



    private String body;

    private Boolean isUnread;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar createdDate;

    private Long chatId;// foreign key to chat table

    private Long senderId;// foreign key to profile table
}
