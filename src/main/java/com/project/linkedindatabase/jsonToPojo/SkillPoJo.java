package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.skill.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillPoJo {
    private Long id;

    private String name;

    private Long profileId;

    private List<EndorsementPoJo> endorsementList = new ArrayList<>();

    public static SkillPoJo convertTOJson(Skill skill)
    {
        SkillPoJo skillPoJo = new SkillPoJo();

        skillPoJo.setId(skill.getId());
        skillPoJo.setProfileId(skill.getProfileId());
        skillPoJo.setName(skill.getName());


        return skillPoJo;
    }
}
