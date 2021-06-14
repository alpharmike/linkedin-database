package com.project.linkedindatabase.domain.skill;


import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Endorsement extends BaseEntity {


    public Long skillId;// foreign key to skill table

    private Long skillLevel;// foreign key --> enum

    private Long relationKnowledge;//   foreign key --> enum

    public Long endorserId;// foreign key to profile table.





}
