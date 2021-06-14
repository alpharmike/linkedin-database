package com.project.linkedindatabase.domain.background;

import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Experience extends BaseEntity {

    private Long profileId;

    private String title;

    private Long employmentType; // foreign key to employmentType ==>enum

    private String companyName;

    private String location;

    private Boolean currentlyWorkingRole;

    private Date startDate;

    private Date endDate;

    private String headline;

    private String description;

    private String industry;


}
