package com.soccer.stats.service;

import com.soccer.stats.model.Match;
import com.soccer.stats.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    @Autowired
    private final MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Match> getMatchesByJournee(int journee) {
        return matchRepository.findByMatchday(journee);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> saveMatches(List<Match> matches) {
        return matchRepository.saveAll(matches);
    }
}

