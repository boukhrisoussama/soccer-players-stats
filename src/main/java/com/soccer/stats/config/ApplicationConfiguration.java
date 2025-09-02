package com.soccer.stats.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.soccer.stats"})
@Import({
        WebSecurityConfiguration.class,
        JacksonWebConfiguration.class,
        PersistenceConfiguration.class
})
@PropertySource({"classpath:application.yml"})
public class ApplicationConfiguration {

}
