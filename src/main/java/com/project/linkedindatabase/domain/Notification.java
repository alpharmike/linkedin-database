package com.project.linkedindatabase.domain;

import com.project.linkedindatabase.annotations.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "notification")
public class Notification extends BaseEntity {

    private Long profileId;

    private Long notificationType;// foreign key to notificationType ==>enum

    private Long targetProfileId;//notification will send for him

    private String text;


}
