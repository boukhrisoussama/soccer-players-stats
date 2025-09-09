package com.soccer.stats.service;


import com.soccer.stats.model.Player;
import com.soccer.stats.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(String id) {
        return playerRepository.findById(id);
    }

    public Player updatePlayer(String id, Player player) {
        return playerRepository.findById(id)
                .map(existingPlayer -> {
                    existingPlayer.setFirstName(player.getFirstName());
                    existingPlayer.setLastName(player.getLastName());
                    existingPlayer.setBirthDate(player.getBirthDate());
                    existingPlayer.setPosition(player.getPosition());
                    existingPlayer.setNationality(player.getNationality());
                    existingPlayer.setMatchesPlayed(player.getMatchesPlayed());
                    existingPlayer.setGoalsScored(player.getGoalsScored());
                    existingPlayer.setAssistsMade(player.getAssistsMade());
                    existingPlayer.setYellowCards(player.getYellowCards());
                    existingPlayer.setRedCards(player.getRedCards());
                    existingPlayer.setTransfer(player.getTransfer());
                    return playerRepository.save(existingPlayer);
                })
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    public void deletePlayer(String id) {
        playerRepository.deleteById(id);
    }

  public List<Player> getPlayersByTeam(String teamId) {
    return playerRepository.findByTeamId(teamId);
  }
}
