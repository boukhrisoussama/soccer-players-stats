package com.soccer.stats.repository;

import com.soccer.stats.model.PlayerMatchStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMatchStatsRepository extends JpaRepository<PlayerMatchStats, String> {
}
