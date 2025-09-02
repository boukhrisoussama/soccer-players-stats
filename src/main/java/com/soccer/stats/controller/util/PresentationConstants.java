package com.soccer.stats.controller.util;

import java.util.Arrays;
import java.util.List;

public interface PresentationConstants {

    public static final String[] pathsToSkip = new String[]{
            "/actuator/health", "/actuator/metrics", "/actuator/metrics/**", "/v3/api-docs/**",
            "/swagger-ui/**", "/swagger-ui.html", "/h2-console/**", "/authentication/**",
            "/favicon.ico", "/authentication/signup", "/authentication/signin", "/authentication/logout", "/resources", "/error"
    };

}
