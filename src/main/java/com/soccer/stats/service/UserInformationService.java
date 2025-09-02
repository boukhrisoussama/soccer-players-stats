package com.soccer.stats.service;

import com.soccer.stats.model.security.SignUpRequest;
import com.soccer.stats.model.security.UserInformation;
import com.soccer.stats.repository.AddressRepository;
import com.soccer.stats.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class UserInformationService implements UserDetailsService {

    @Autowired
    UserInformationRepository userInfoRepository;

    @Autowired
    AddressRepository addressRepository;

    BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();

    @Override
    public UserInformation loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
    }

    public UserInformation createUserInformation(SignUpRequest signUpRequest) {

        String roles = String.join(" ",  getUserAuthorities(signUpRequest));
        UserInformation userInformation = new UserInformation(signUpRequest.getUserName(),
                passwordEncorder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getBirthDate(), signUpRequest.getEmail(),
                List.of(signUpRequest.getAddress()),
                roles
                );
        return saveUserInformation(userInformation);
    }

    private String[] getUserAuthorities(SignUpRequest signUpRequest) {
        return signUpRequest.getAuthorities().toArray(new String[0]);
    }

    public UserInformation saveUserInformation(UserInformation userInfo) {
        if(userInfo != null) {
            return userInfoRepository.save(userInfo);
        } else throw new UnsupportedOperationException("-- User information is null !");
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserInformation> findAll() throws AccessDeniedException {
        return userInfoRepository.findAll();
    }

}
