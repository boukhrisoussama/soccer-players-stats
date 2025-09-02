package com.soccer.stats.repository;

import com.soccer.stats.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    // This interface will automatically provide CRUD operations for Player entities
    // Additional custom query methods can be defined here if needed
}
