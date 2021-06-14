package com.project.linkedindatabase.domain.background;

import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Education extends BaseEntity {


    private Long profileId;

    private String school;

    private String degree;

    private String fieldOfStudy;

    private Date startDate;

    private Date endDate;

    private String grade;

    private String activitiesAndSocieties;

    private String description;


}
