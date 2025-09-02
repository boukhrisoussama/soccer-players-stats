package com.soccer.stats;

import com.soccer.stats.model.Player;
import com.soccer.stats.model.Team;
import com.soccer.stats.repository.PlayerRepository;
import com.soccer.stats.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class SoccerPlayersStatsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SoccerPlayersStatsApplication.class, args);
        TeamRepository teamRepository = context.getBean(TeamRepository.class);
        PlayerRepository playerRepository = context.getBean(PlayerRepository.class);
        CommandLineRunner dataLoader = null;
        try {
            dataLoader = loadCsvData(teamRepository, playerRepository);
        } catch (IOException e) {
            log.error("Error loading CSV data", e);
            throw new RuntimeException(e);
        }
        if (context.isActive() && dataLoader != null) {
            log.info("Tunisian Sports Club & Players are loaded to the Application successfully!");
        }
    }

    public static CommandLineRunner loadCsvData(TeamRepository teamRepository, PlayerRepository playerRepository) throws IOException {
        return args -> {
            // Load Teams
            log.info("Loading Teams data from CSV...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new ClassPathResource("data/teams.csv").getInputStream(), StandardCharsets.UTF_8))) {
                String line = reader.readLine(); // skip header
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",", -1);
                    if (fields.length < 9) continue;
                    Team team = Team.builder()
                            // id is generated, do not set
                            .name(fields[1])
                            .foundedYear(StringUtils.hasText(fields[2]) ? parseInt(fields[2]) : null)
                            .president(fields[3])
                            .stadium(fields[4])
                            .city(fields[5])
                            .country(fields[6])
                            .website(fields[7])
                            .logoUrl(fields[8])
                            .build();
                    teamRepository.save(team);
                }
            }

            // Load Players
            log.info("Loading Players data from CSV...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new ClassPathResource("data/players.csv").getInputStream(), StandardCharsets.UTF_8))) {
                String line = reader.readLine(); // skip header
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",", -1);
                    if (fields.length < 12) continue;
                    Player player = Player.builder()
                            .id(fields[0])
                            .firstName(fields[1])
                            .lastName(fields[2])
                            .birthDate(StringUtils.hasText(fields[3]) ? LocalDate.parse(fields[3]) : null)
                            .position(fields[4])
                            .nationality(fields[5])
                            .matchesPlayed(parseInt(fields[6]))
                            .goalsScored(parseInt(fields[7]))
                            .assistsMade(parseInt(fields[8]))
                            .yellowCards(parseInt(fields[9]))
                            .redCards(parseInt(fields[10]))
                            .minutesPlayed(parseInt(fields[11]))
                            // transferHistory is not loaded from CSV
                            .build();
                    playerRepository.save(player);
                }
            }
            // Data loading completed;
        };
    }

    private static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            log.error("Error parsing integer: {}", s, e);
            return 0;
        }
    }
}
