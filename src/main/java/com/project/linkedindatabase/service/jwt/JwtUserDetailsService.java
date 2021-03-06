package com.project.linkedindatabase.service.jwt;

import com.project.linkedindatabase.config.JwtTokenUtil;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.service.model.ProfileService;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Component
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final ProfileService profileService;

    public JwtUserDetailsService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileService.findByUsername(username);
        if (profile == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {

            return new User(profile.getUsername(), profile.getPassword(), new ArrayList<>());

        }
    }

    @SneakyThrows
    public Profile loadProfileByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileService.findByUsername(username);
        if (profile == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            return profile;
        }
    }

    public Profile getProfileByHeader(Map<String,Object> jsonHeader)  throws Exception
    {

        String token = getTokenByHeader(jsonHeader);
        String username = new JwtTokenUtil().getUsernameFromToken(token);

        return loadProfileByUsername( username );
    }



    public static String getTokenByHeader(Map<String,Object> jsonHeader)
    {
       return (String) jsonHeader.get(JwtTokenUtil.TOKEN);
    }
}
