package com.project.linkedindatabase.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notification {

    private Long profileId;

    private Long notificationType;// foreign key to notificationType ==>enum

    private Long targetProfileId;//notification will send for him

    private String text;


}
