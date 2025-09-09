package com.soccer.stats.repository;

import com.soccer.stats.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

  List<Player> findByTeamId(String teamId);
    // This interface will automatically provide CRUD operations for Player entities
    // Additional custom query methods can be defined here if needed
}
