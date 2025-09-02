package com.soccer.stats.controller;

import com.soccer.stats.model.security.LoginRequest;
import com.soccer.stats.model.security.SignUpRequest;
import com.soccer.stats.model.security.UserTokenState;
import com.soccer.stats.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static java.text.MessageFormat.format;

@Slf4j
@RestController
@RequestMapping(value = "/authentication",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserTokenState> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<UserTokenState> signin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(Objects.requireNonNull(request)));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String originUrl = request.getHeader(HttpHeaders.ORIGIN);
        Object[] params = {"logout", originUrl};
        String logoutUrl = format("{0}/logout?returnTo={1}", params);
        request.getSession().invalidate();
        return ResponseEntity.ok().body(logoutUrl);
    }

}
