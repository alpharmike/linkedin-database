package com.project.linkedindatabase.domain.chat;

import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "chat")
public class Chat extends BaseEntity {

    private Boolean isArchive;

    private Boolean markUnread;

    private Long profileId1;// foreign key to profile table.

    private Long profileId2;// foreign key to profile table.

    // profileId1 and profileId2 indicate user that have chat with each other

}
