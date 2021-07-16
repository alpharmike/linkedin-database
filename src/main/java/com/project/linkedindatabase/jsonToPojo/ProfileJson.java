
package com.project.linkedindatabase.jsonToPojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.utils.SHAHashing;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileJson {

    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private Long phoneType;// foreign key to phoneType ==>enum
    private String phoneTypeName;


    private String password;

    private String firstName;

    private String lastName;

    private String formerName;

    private Long formerNameVisibilityType;// foreign key to formerNameVisibility ==>enum
    private String formerNameVisibilityTypeName;

    private String headline;

    private Long currentPositionId;// foreign key to Experience table
    private BackgroundJson currentPosition;

    private Boolean showCurrentPositionId;

    private Long currentEducationId;
    private BackgroundJson currentEducation;

    private Boolean showCurrentEducationId;

    private String country;

    private String locationInCountry;

    private Long industry;// foreign key to  industry
    private String industryName;

    private String address;

    private String dateOfBirth;

    private String about;

    private String urlToProfile;

    public Profile convertToProfile() throws Exception
    {
        return this.convertToProfile(false);
    }

    public Profile convertToProfile(boolean isSignUp) throws Exception
    {
        // date of birth : because it is different in every situation we won't convert it here

        if ( isSignUp && (username == null || password == null || email == null|| phoneNumber == null))
        {
            throw new Exception("some field are null");
        }
        Profile profile = new Profile();
        profile.setId(getId());
        profile.setUsername(this.username);
        profile.setEmail(this.email);
        profile.setPhoneNumber(this.phoneNumber);
        profile.setPhoneType(getPhoneType());
        profile.setPassword( new BCryptPasswordEncoder().encode( (this.password )));
        profile.setFirstName(this.firstName);
        profile.setLastName(this.lastName);
        profile.setFormerName(getFormerName());
        profile.setFormerNameVisibilityType(getFormerNameVisibilityType());
        profile.setHeadline(getHeadline());
        profile.setCurrentPositionId(getCurrentPositionId());
        profile.setShowCurrentPositionId(getShowCurrentPositionId());
        profile.setCurrentEducationId(getCurrentEducationId());
        profile.setShowCurrentEducationId(getShowCurrentEducationId());
        profile.setCountry(this.country);
        profile.setLocationInCountry(this.locationInCountry);
        profile.setIndustry(this.industry);
        profile.setAddress(getAddress());

        profile.setAbout(getAbout());
        profile.setUrlToProfile(getUrlToProfile());


        return profile;
    }

    public static ProfileJson convertToJson(Profile profile)
    {
        ProfileJson profileJson = new ProfileJson();


        profileJson.setId(profile.getId());
        profileJson.setUsername(profile.getUsername());
        profileJson.setEmail(profile.getEmail());
        profileJson.setPhoneNumber(profile.getPhoneNumber());
        profileJson.setPhoneType(profile.getPhoneType());

        profileJson.setFirstName(profile.getFirstName());
        profileJson.setLastName(profile.getLastName());
        profileJson.setFormerName(profile.getFormerName());
        profileJson.setFormerNameVisibilityType(profile.getFormerNameVisibilityType());
        profileJson.setHeadline(profile.getHeadline());
        profileJson.setCurrentPositionId(profile.getCurrentPositionId());
        profileJson.setShowCurrentPositionId(profile.getShowCurrentPositionId());
        profileJson.setCurrentEducationId(profile.getCurrentEducationId());
        profileJson.setShowCurrentEducationId(profile.getShowCurrentEducationId());
        profileJson.setCountry(profile.getCountry());
        profileJson.setLocationInCountry(profile.getLocationInCountry());
        profileJson.setIndustry(profile.getIndustry());
        profileJson.setAddress(profile.getAddress());

        profileJson.setAbout(profile.getAbout());
        profileJson.setUrlToProfile(profile.getUrlToProfile());
        return profileJson;

    }

}
