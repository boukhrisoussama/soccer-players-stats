package com.soccer.stats.service;

import com.soccer.stats.model.Player;
import com.soccer.stats.model.Team;
import com.soccer.stats.repository.PlayerRepository;
import com.soccer.stats.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PlayersDbInitService {

  private final static Map<String, Team> loadedTeams = new HashMap<>();
  private final static Map<String, Player> loadedPlayers = new HashMap<>();

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  PlayerRepository playerRepository;

  public void initializeTeamsDatabase() throws IOException {

    log.info("Loading Teams data from CSV...");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
      new ClassPathResource("data/teams.csv").getInputStream(), StandardCharsets.UTF_8))) {
      String line = reader.readLine(); // skip header
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",", -1);
        if (fields.length < 9) continue;
        Team team = Team.builder()
          .id(fields[0])
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
        loadedTeams.put(team.getId(), team);
      }
    }
  }

  public void initializePlayersDatabase() throws IOException {

    // Load Players
    log.info("Loading Players data from CSV...");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
      new ClassPathResource("data/players.csv").getInputStream(), StandardCharsets.UTF_8))) {
      String line = reader.readLine(); // skip header
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",", -1);
        if (fields.length < 12) continue;
        Player player = Player.builder()
//          .id(fields[0])
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
          .team(getTeamFromPlayerId(fields[0]))  // Assuming first two chars of player ID map to team ID
          // transferHistory is not loaded from CSV
          .build();
        playerRepository.save(player);
        loadedPlayers.put(player.getId(), player);
      }
    }
  }

  private Team getTeamFromPlayerId(String field) {
    String teamId = field.substring(0, 1);
    if (loadedTeams.containsKey(teamId)) {
      return loadedTeams.get(teamId);
    } else {
      teamId = field.substring(0, 2);
      if (loadedTeams.containsKey(teamId)) {
        return loadedTeams.get(teamId);
      } else {
        teamId = field.substring(0, 3);
        if (loadedTeams.containsKey(teamId)) {
          return loadedTeams.get(teamId);
        }
      }
    }
    return null;
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
