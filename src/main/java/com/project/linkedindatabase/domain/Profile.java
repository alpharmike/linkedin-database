package com.project.linkedindatabase.domain;

import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Table(tableName = "profile")
public class Profile extends BaseEntity {

    private String email;

    private String phoneNumber;

    private Long phoneType;// foreign key to phoneType ==>enum

    private String password;

    private String firstName;

    private String lastName;

    private String formerName;

    private Long formerNameVisibilityType;// foreign key to formerNameVisibility ==>enum

    private String headline;

    private Long currentPositionId;// foreign key to Experience table

    private Boolean showCurrentPositionId;

    private Long currentEducationId;// foreign key to Education table

    private Boolean showCurrentEducationId;

    private String country;

    private String locationInCountry;

    private Long industry;// foreign key to  industry

    private String address;

    private Date dateOfBirth;

    private String about;

    private String urlTOProfile;

}
