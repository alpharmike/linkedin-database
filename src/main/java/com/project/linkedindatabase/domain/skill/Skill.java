package com.project.linkedindatabase.domain.skill;


import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Skill extends BaseEntity {



    private String name;//it's name of skill for example android,django

    private Long profileId;//foreign key
}
