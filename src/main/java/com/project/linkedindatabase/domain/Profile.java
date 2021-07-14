package com.project.linkedindatabase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.Annotation;
import java.util.Calendar;

@Setter
@Getter
@NoArgsConstructor
@Table(tableName = "profile")
public class Profile extends BaseEntity {
    private String username;

    private String email;

    private String phoneNumber;

    private Long phoneType;// foreign key to phoneType ==>enum

    @JsonIgnore
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

    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar dateOfBirth;

    private String about;

    private String urlToProfile;



}
