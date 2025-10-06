package com.soccer.stats;

import com.soccer.stats.service.PlayersDbInitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class SoccerPlayersStatsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SoccerPlayersStatsApplication.class, args);
        PlayersDbInitService initService = context.getBean(PlayersDbInitService.class);
      try {
        initService.initializeAllDatabases();
      } catch (IOException e) {
        log.error("Failed to initialize database", e);
        throw new RuntimeException(e);
      }

    }

}
