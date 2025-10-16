package com.soccer.stats.service;

import com.soccer.stats.model.Match;
import com.soccer.stats.model.Team;
import com.soccer.stats.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchScraperService {

  private final MatchService matchService;
  private final TeamService teamService;

  private static final String BASE_URL = "https://www.ftf.org.tn/fr/calandrier-et-resultats-ligue-1/";
    private static final String SEASON = "2024-2025";

    public List<Match> scrapeMatches(int journee) {
        List<Match> matches = new ArrayList<>();
        try {
            String url = String.format("%s?journee=%d&season=%s", BASE_URL, journee, SEASON);
            Document doc = Jsoup.connect(url).get();
            Elements matchElements = doc.select("table.matches-table tr");

            for (Element matchElement : matchElements) {
                Match match = parseMatchElement(matchElement, journee);
                if (match != null) {
                    matches.add(match);
                }
            }
        } catch (IOException e) {
            log.error("Error scraping matches for journee {}: {}", journee, e.getMessage());
        }
        return matches;
    }

    private Match parseMatchElement(Element element, int journee) {
        try {
            Elements columns = element.select("td");
            if (columns.size() < 6) return null;

            String dateStr = columns.get(0).text();
            String timeStr = columns.get(1).text();
            String homeTeamId = columns.get(2).text();
            String score = columns.get(3).text();
            String awayTeamId = columns.get(4).text();
            String stadium = columns.get(5).text();

          Team homeTeam = teamService.getTeamById(homeTeamId).orElse(null);
          Team awayTeam = teamService.getTeamById(awayTeamId).orElse(null);

            Match match = new Match();
            match.setMatchday(journee);
            match.setDate(parseDateTime(dateStr, timeStr));
            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            match.setStadium(stadium);

            if (!score.trim().isEmpty()) {
                String[] scores = score.split("-");
                match.setScoreHome(Integer.parseInt(scores[0].trim()));
                match.setScoreAway(Integer.parseInt(scores[1].trim()));
            }

            return match;
        } catch (Exception e) {
            log.error("Error parsing match element: {}", e.getMessage());
            return null;
        }
    }

    private LocalDateTime parseDateTime(String dateStr, String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateStr + " " + timeStr, formatter);
    }

    public void updateAllMatches() {
        for (int journee = 1; journee <= 26; journee++) {
            List<Match> matches = scrapeMatches(journee);
            matchService.saveMatches(matches);
        }
    }
}

