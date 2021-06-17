package com.project.linkedindatabase.domain.accomplishment;


import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "accomplishment")
public class Accomplishment extends BaseEntity {
    private Long profileId;

    private String subject;

    private String description;

    private Long accomplishmentType;// foreign key --> enum

    private Byte[] file;//if it has file

}
