package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.skill.Endorsement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EndorsementPoJo {

    private Long id;

    private Long skillId;

    private Long skillLevel;

    private String skillLevelName;

    private Long relationKnowledge;

    private String relationKnowledgeName;

    private Long endorserId;

    private ProfileJson profileJson;

    public static EndorsementPoJo convertToJson(Endorsement endorsement)
    {
        EndorsementPoJo endorsementPoJo = new EndorsementPoJo();
        endorsementPoJo.setId(endorsement.getId());
        endorsementPoJo.setSkillId(endorsement.getSkillId());
        endorsementPoJo.setSkillLevel(endorsement.getSkillLevel());
        endorsementPoJo.setRelationKnowledge(endorsement.getRelationKnowledge());
        endorsementPoJo.setEndorserId(endorsement.getEndorserId());


        return endorsementPoJo;
    }

}
