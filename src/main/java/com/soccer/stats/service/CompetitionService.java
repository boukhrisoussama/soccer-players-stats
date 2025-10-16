package com.soccer.stats.service;

import com.soccer.stats.model.Competition;
import com.soccer.stats.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

  @Autowired
  CompetitionRepository competitionRepository;


  public List<Competition> getAllCompetitions() {
    return competitionRepository.findAll();
  }

}
