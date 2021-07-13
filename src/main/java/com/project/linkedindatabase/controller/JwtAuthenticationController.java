package com.project.linkedindatabase.controller;

import java.util.Objects;

import com.project.linkedindatabase.config.JwtTokenUtil;
import com.project.linkedindatabase.model.JwtRequest;
import com.project.linkedindatabase.model.JwtResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {


        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            log.warn("before");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.warn("after");
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.warn("BAD PASSWORD");
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
