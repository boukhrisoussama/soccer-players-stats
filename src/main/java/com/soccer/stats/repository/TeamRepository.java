package com.soccer.stats.repository;

import com.soccer.stats.model.Player;
import com.soccer.stats.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    List<Team> findByCountry(String country);
    List<Team> findByNameContainingIgnoreCase(String name);

    default Object getTeamStats() { return List.of(); }

  List<Team> findByCountryIgnoreCase(String country);
}
