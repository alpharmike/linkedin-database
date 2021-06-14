package com.project.linkedindatabase.domain.chat;


import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity {



    private String text;

    private Boolean isUnRead;

    private Date Date;

    private Long chatId;// foreign key to chat table

    private Long senderId;// foreign key to profile table
}
