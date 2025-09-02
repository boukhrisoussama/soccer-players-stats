package com.soccer.stats.repository;

import com.soccer.stats.model.Player;
import com.soccer.stats.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    // This interface will automatically provide CRUD operations for Team entities
    // Additional custom query methods can be defined here if needed
}

