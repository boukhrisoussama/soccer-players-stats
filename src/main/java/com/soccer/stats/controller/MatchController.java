package com.soccer.stats.controller;

import com.soccer.stats.model.Match;
import com.soccer.stats.service.MatchScraperService;
import com.soccer.stats.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final MatchScraperService matchScraperService;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/journee/{n}")
    public List<Match> getMatchesByJournee(@PathVariable int n) {
        return matchService.getMatchesByJournee(n);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateMatches() {
        matchScraperService.updateAllMatches();
        return ResponseEntity.ok().build();
    }
}
