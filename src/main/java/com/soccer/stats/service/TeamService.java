package com.soccer.stats.service;

import com.soccer.stats.model.Team;
import com.soccer.stats.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(String id) {
        return teamRepository.findById(id);
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(String id, Team team) {
        return teamRepository.findById(id)
                .map(existingTeam -> {
                    existingTeam.setName(team.getName());
                    existingTeam.setFoundedYear(team.getFoundedYear());
                    existingTeam.setPresident(team.getPresident());
                    existingTeam.setStadium(team.getStadium());
                    existingTeam.setCity(team.getCity());
                    existingTeam.setCountry(team.getCountry());
                    existingTeam.setWebsite(team.getWebsite());
                    existingTeam.setLogoUrl(team.getLogoUrl());
                    return teamRepository.save(existingTeam);
                })
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }

    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }
}
