package com.soccer.stats.controller;

import com.soccer.stats.service.CompetitionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/competitions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "competitions", description = "Competitions management API")
public class CompetitionController {

  @Autowired
  CompetitionService competitionService;

  @GetMapping
  public ResponseEntity<?> getCompetitions() {
    return ResponseEntity.ok(competitionService.getAllCompetitions());
  }

}
