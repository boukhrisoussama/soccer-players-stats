package com.soccer.stats.controller;

import com.soccer.stats.model.Player;
import com.soccer.stats.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@Tag(name = "Players", description = "Player management API")
public class PlayersController {

    @Autowired
    PlayerService playerService;

    @Operation(summary = "Get all players")
    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Get player by ID")
    @GetMapping("/{id}")
    public Optional<Player> getPlayer(@PathVariable String id) {
        return playerService.getPlayerById(id);
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
