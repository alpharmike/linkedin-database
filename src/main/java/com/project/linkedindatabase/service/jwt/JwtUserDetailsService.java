package com.project.linkedindatabase.service.jwt;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.model.ProfileRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUsername(username);
        if (profile == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            return new User(profile.getUsername(), profile.getPassword(), new ArrayList<>());
        }
    }
}
