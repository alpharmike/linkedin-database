
package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.utils.SHAHashing;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpData {


    private String country;

    private String dateOfBirth;

    private String email;

    private String firstName;

    private Long industry;

    private String lastName;

    private String locationInCountry;

    private String password;

    private String phoneNumber;

    private String username;

    public Profile convertToProfile() throws Exception
    {
        // date of birth : because it is different in every situation we won't convert it here

        if (username == null || password == null || email == null|| phoneNumber == null)
        {
            throw new Exception("some field are null");
        }
        Profile profile = new Profile();
        profile.setCountry(this.country);
        profile.setUsername(this.username);
        profile.setEmail(this.email);
        profile.setFirstName(this.firstName);
        profile.setIndustry(this.industry);
        profile.setLastName(this.lastName);
        profile.setLocationInCountry(this.locationInCountry);
        profile.setPassword( new BCryptPasswordEncoder().encode( (this.password )));
        profile.setPhoneNumber(this.phoneNumber);

        return profile;
    }

}
