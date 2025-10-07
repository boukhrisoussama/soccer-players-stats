package com.soccer.stats.service;

import com.soccer.stats.model.*;
import com.soccer.stats.repository.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.soccer.stats.model.Player;
import com.soccer.stats.model.Team;
import com.soccer.stats.repository.FederationRepository;
import com.soccer.stats.repository.PlayerRepository;
import com.soccer.stats.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
  private final static Map<String, Competition> loadedCompetitions = new HashMap<>();
  private final static Map<String, Match> loadedMatches = new HashMap<>();
  private final static Map<String, Transfer> loadedTransfers = new HashMap<>();
  private final static Map<String, PlayerMatchStats> loadedPlayerMatchStats = new HashMap<>();

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  PlayerRepository playerRepository;
  @Autowired
  FederationRepository federationRepository;
  @Autowired
  CompetitionRepository competitionRepository;
  @Autowired
  MatchRepository matchRepository;
  @Autowired
  TransferRepository transferRepository;
  @Autowired
  PlayerMatchStatsRepository playerMatchStatsRepository;

  public void initializeCompetitionsDatabase() throws IOException {
    log.info("Loading Competitions data from CSV...");
    int count = 0;
    Federation federationTunisienne = new Federation();
    federationTunisienne.setCountry("Tunisia");
    federationTunisienne.setName("Fédération Tunisienne de Football");
    federationTunisienne.setInternational(true);
    federationRepository.save(federationTunisienne);
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
      new ClassPathResource("data/competitions.csv").getInputStream(), StandardCharsets.UTF_8))) {
      String line = reader.readLine(); // skip header
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) continue;
        String[] fields = line.split(",", -1);
        try {
          Competition existing = competitionRepository.findById(fields[0].trim()).orElse(null);
          if (existing == null) {
            Competition competition = Competition.builder()
              .id(fields[0].trim())
              .name(fields[1].trim())
              .country(fields[2].trim())
              .type(CompetitionType.valueOf(fields[3].trim().toUpperCase()))
              .build();
            competitionRepository.save(competition);
            loadedCompetitions.put(competition.getId(), competition);
            count++;
          } else {
            loadedCompetitions.put(existing.getId(), existing);
          }
        } catch (Exception e) {
          log.warn("Could not parse competition row: {}", line, e);
        }
      }
    }
    log.info("Loaded {} competitions from competitions.csv", count);
  }

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

  public void initializeMatchesDatabase() throws IOException {
    log.info("Loading Matches data from CSV...");
    int count = 0;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
      new ClassPathResource("data/matches.csv").getInputStream(), StandardCharsets.UTF_8))) {
      String line = reader.readLine(); // skip header
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) continue;
        String[] fields = line.split(",", -1);
        try {
          Match existing = matchRepository.findById(fields[0].trim()).orElse(null);
          if (existing == null) {
            Match match = Match.builder()
              .id(fields[0].trim())
              .competition(loadedCompetitions.get(fields[1].trim()))
              .homeTeam(loadedTeams.get(fields[2].trim()))
              .awayTeam(loadedTeams.get(fields[3].trim()))
              .date(LocalDateTime.parse(fields[4].trim(), java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
              .stadium(fields[5].trim())
              .scoreHome(parseInt(fields[6].trim()))
              .scoreAway(parseInt(fields[7].trim()))
              .playerStats(new ArrayList<>())
              .build();
            matchRepository.save(match);
            loadedMatches.put(match.getId(), match);
            count++;
          } else {
            loadedMatches.put(existing.getId(), existing);
          }
        } catch (Exception e) {
          log.warn("Could not parse match row: {}", line, e);
        }
      }
    }
    log.info("Loaded {} matches from matches.csv", count);
  }

  public void initializeTransfersDatabase() throws IOException {
    log.info("Loading Transfers data from CSV...");
    int count = 0;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
      new ClassPathResource("data/transfers.csv").getInputStream(), StandardCharsets.UTF_8))) {
      String line = reader.readLine(); // skip header
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) continue;
        String[] fields = line.split(",", -1);
        try {
          Transfer existing = transferRepository.findById(fields[0].trim()).orElse(null);
          if (existing == null) {
            Transfer transfer = Transfer.builder()
              .id(fields[0].trim())
              .player(loadedPlayers.get(fields[1].trim()))
              .fromTeam(loadedTeams.get(fields[2].trim()))
              .toTeam(loadedTeams.get(fields[3].trim()))
              .transferDate(LocalDate.parse(fields[4].trim()))
              .transferFee(new BigDecimal(fields[5].trim()))
              .contractYears(parseInt(fields[6].trim()))
              .build();
            transferRepository.save(transfer);
            loadedTransfers.put(transfer.getId(), transfer);
            count++;
          } else {
            loadedTransfers.put(existing.getId(), existing);
          }
        } catch (Exception e) {
          log.warn("Could not parse transfer row: {}", line, e);
        }
      }
    }
    log.info("Loaded {} transfers from transfers.csv", count);
  }

  public void initializePlayerMatchStatsDatabase() throws IOException {
    log.info("Loading Player Match Stats data from CSV...");
    int count = 0;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
      new ClassPathResource("data/player_match_stats.csv").getInputStream(), StandardCharsets.UTF_8))) {
      String line = reader.readLine(); // skip header
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) continue;
        String[] fields = line.split(",", -1);
        try {
          PlayerMatchStats existing = playerMatchStatsRepository.findById(fields[0].trim()).orElse(null);
          if (existing == null) {
            PlayerMatchStats stats = PlayerMatchStats.builder()
              .id(fields[0].trim())
              .player(loadedPlayers.get(fields[1].trim()))
              .match(loadedMatches.get(fields[2].trim()))
              .minutesPlayed(parseInt(fields[3].trim()))
              .goals(parseInt(fields[4].trim()))
              .assists(parseInt(fields[5].trim()))
              .yellowCards(parseInt(fields[6].trim()))
              .redCards(parseInt(fields[7].trim()))
              .rating(Double.parseDouble(fields[8].trim()))
              .build();
            playerMatchStatsRepository.save(stats);
            loadedPlayerMatchStats.put(stats.getId(), stats);
            count++;
          } else {
            loadedPlayerMatchStats.put(existing.getId(), existing);
          }
        } catch (Exception e) {
          log.warn("Could not parse player match stats row: {}", line, e);
        }
      }
    }
    log.info("Loaded {} player match stats from player_match_stats.csv", count);
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

  public void initializeAllDatabases() throws IOException {
    initializeCompetitionsDatabase();
    initializeTeamsDatabase();
    initializePlayersDatabase();
    initializeTransfersDatabase();
    initializeMatchesDatabase();
    initializePlayerMatchStatsDatabase();
  }
}
