package com.project.linkedindatabase.jsonToPojo;


import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccomplishmentJson {
    private Long id;
    private Long profileId;

    private String subject;

    private String description;

    private Long accomplishmentType;
    private String accomplishmentTypeName;

    private Byte[] file;


    public Accomplishment convertToAccomplishment() throws ParseException {
        Accomplishment accomplishment = new Accomplishment();
        accomplishment.setAccomplishmentType(getAccomplishmentType());
        accomplishment.setDescription(getDescription());
        accomplishment.setFile(getFile());
        accomplishment.setSubject(getSubject());
        accomplishment.setProfileId(getProfileId());
        accomplishment.setId(getId());
        return accomplishment;
    }


    public static AccomplishmentJson convertToJson(Accomplishment accomplishment)  {
        AccomplishmentJson accomplishmentJson = new AccomplishmentJson();
        accomplishmentJson.setAccomplishmentType(accomplishment.getAccomplishmentType());
        accomplishmentJson.setDescription(accomplishment.getDescription());
        accomplishmentJson.setFile(accomplishment.getFile());
        accomplishmentJson.setSubject(accomplishment.getSubject());
        accomplishmentJson.setProfileId(accomplishment.getProfileId());
        accomplishmentJson.setId(accomplishment.getId());
        return accomplishmentJson;

    }
}
