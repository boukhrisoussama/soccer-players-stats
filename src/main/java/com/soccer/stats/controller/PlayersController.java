package com.soccer.stats.controller;

import com.soccer.stats.model.Player;
import com.soccer.stats.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/players", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Players", description = "Player management API")
public class PlayersController {

    @Autowired
    PlayerService playerService;

    @Operation(summary = "Get all players")
    @GetMapping
    public List<Player> getPlayers(@RequestParam("teamId") String teamId) {
        return (teamId != null ? playerService.getPlayersByTeam(teamId) : playerService.getAllPlayers());
    }

    @Operation(summary = "Get player by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") String id) {
        return playerService.getPlayerById(id)
          .filter(player -> StringUtils.isNotBlank(player.getId()))
          .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new player")
    @PostMapping
    public Player addPlayer(@Valid @RequestBody Player player) {
        return playerService.addPlayer(player);
    }

    @Operation(summary = "Update a player")
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable String id, @Valid @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @Operation(summary = "Delete a player")
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable String id) {
        playerService.deletePlayer(id);
    }
}
