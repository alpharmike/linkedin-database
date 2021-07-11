package com.project.linkedindatabase.domain.chat;


import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "message")
public class Message extends BaseEntity {



    private String text;

    private Boolean isUnread;

    private Timestamp createdDate;

    private Long chatId;// foreign key to chat table

    private Long senderId;// foreign key to profile table
}
