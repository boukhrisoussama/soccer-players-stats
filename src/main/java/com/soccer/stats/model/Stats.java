package com.soccer.stats.model;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    // Match stats
    private int totalMatches;
    private double averageGoalsPerMatch;
    private int totalGoals;

    // Team stats
    private int totalTeams;
    private int homeWins;
    private int awayWins;
    private int draws;

    // Player stats
    private List<TopScorer> topScorers;
    private List<TopAssist> topAssists;

    // Season specific stats
    private String season;
    private Integer matchesPlayed;
    private Integer matchesRemaining;
    private Double seasonProgress;

    // Additional stats
    private Integer cleanSheets;
    private Integer yellowCards;
    private Integer redCards;

    private Date lastUpdated;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopScorer {
        private String playerId;
        private String playerName;
        private int goals;
        private String team;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopAssist {
        private String playerId;
        private String playerName;
        private int assists;
        private String team;
    }
}

