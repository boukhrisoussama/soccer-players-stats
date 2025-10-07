package com.soccer.stats.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "MATCHES")
public class Match implements ModelObject {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Team awayTeam;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "score_home")
    private Integer scoreHome;

    @Column(name = "score_away")
    private Integer scoreAway;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<PlayerMatchStats> playerStats;
}
