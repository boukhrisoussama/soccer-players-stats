package com.soccer.stats.controller;

import com.soccer.stats.model.Team;
import com.soccer.stats.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
@Tag(name = "Teams", description = "Team management API")
public class TeamsController {

    @Autowired
    TeamService teamService;

    @Operation(summary = "Get all teams")
    @GetMapping
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }

    @Operation(summary = "Get team by ID")
    @GetMapping("/{id}")
    public Optional<Team> getTeam(@PathVariable String id) {
        return teamService.getTeamById(id);
    }

    @Operation(summary = "Add a new team")
    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }

    @Operation(summary = "Update a team")
    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable String id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @Operation(summary = "Delete a team")
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
    }
}
