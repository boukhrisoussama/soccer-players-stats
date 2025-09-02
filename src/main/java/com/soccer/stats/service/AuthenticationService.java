package com.soccer.stats.service;

import com.soccer.stats.model.security.LoginRequest;
import com.soccer.stats.model.security.SignUpRequest;
import com.soccer.stats.model.security.UserInformation;
import com.soccer.stats.model.security.UserTokenState;
import com.soccer.stats.security.JwtTokenManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private static final Long EXPIRES_IN = 30000L;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    UserInformationService userInformationService;

    public UserTokenState signin(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailIdentifier(), loginRequest.getAppPassword())
        );
        UserInformation userDetails = (UserInformation) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        String jwt = jwtTokenManager.generateJwtToken(userDetails.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new UserTokenState(jwt, (long) EXPIRES_IN);
    }


    public UserTokenState signup(SignUpRequest signUpRequest) {

        userInformationService.createUserInformation(signUpRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword())
        );

        UserInformation userDetails = (UserInformation) authentication.getPrincipal();
        String jwt = jwtTokenManager.generateJwtToken(userDetails.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new UserTokenState(jwt, (long) EXPIRES_IN);
    }
}
