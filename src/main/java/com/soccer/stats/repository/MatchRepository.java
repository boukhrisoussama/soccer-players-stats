package com.soccer.stats.repository;

import com.soccer.stats.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {
    List<Match> findByMatchday(Integer matchday);
    List<Match> findByCompetitionId(String competitionId);
}
