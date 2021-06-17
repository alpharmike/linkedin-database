package com.project.linkedindatabase.domain.skill;


import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "skill")
public class Skill extends BaseEntity {



    private String name;//it's name of skill for example android,django

    private Long profileId;//foreign key
}
