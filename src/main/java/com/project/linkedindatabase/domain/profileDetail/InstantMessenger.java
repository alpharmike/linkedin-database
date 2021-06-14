package com.project.linkedindatabase.domain.profileDetail;


import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstantMessenger extends BaseEntity {



    private Long profileId;

    private String messengerUsername;// the way witch they can connect

    private Long messengerType;// foreign key --> enum
}
