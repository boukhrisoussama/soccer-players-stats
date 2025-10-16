package com.soccer.stats.controller;

import com.soccer.stats.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stats", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Stats", description = "Stats management API")
public class StatsController {

  @Autowired
  private StatsService statsService;

  @Operation(summary = "Get all competitions matches")
  @GetMapping("/matches")
  public ResponseEntity<?> getMatchStats() {
    return ResponseEntity.ok(statsService.getMatchStats());
  }

  @GetMapping("/stats/teams")
  public ResponseEntity<?> getTeamStats() {
    return ResponseEntity.ok(statsService.getTeamStats());
  }

  @GetMapping("/stats/players")
  public ResponseEntity<?> getPlayerStats() {
    return ResponseEntity.ok(statsService.getPlayerStats());
  }

  @GetMapping("/stats/season/{season}")
  public ResponseEntity<?> getSeasonStats(@PathVariable String season) {
    return ResponseEntity.ok(statsService.getSeasonStats(season));
  }

}
