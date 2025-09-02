package com.soccer.stats.security.auth;


import com.soccer.stats.security.JwtTokenManager;
import com.soccer.stats.service.UserInformationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.soccer.stats.controller.util.PresentationConstants.pathsToSkip;


@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserInformationService userDetailsService;

    @Autowired
    JwtTokenManager tokenHelper;

    /*
     * The below paths will get ignored by the filter
     */
    public static final String SIGNUP_MATCHER = "/authentication/signup";
    public static final String SIGNIN_MATCHER = "/authentication/signin";
    public static final String LOGOUT_MATCHER = "/authentication/logout";

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authToken = tokenHelper.getToken(request);
        if (authToken != null && !skipPathRequest(request, List.of(pathsToSkip))) {
            // get username from token
            try {
                String username = tokenHelper.getUserNameFromToken(authToken);
                // get user
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication());
            }
        } else SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication());
        chain.doFilter(request, response);
    }

    private boolean skipPathRequest(HttpServletRequest request, List<String> pathsToSkip) {
        Assert.notNull(pathsToSkip, "path cannot be null.");
        List<RequestMatcher> m = pathsToSkip.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        OrRequestMatcher matchers = new OrRequestMatcher(m);
        return matchers.matches(request);
    }

}
