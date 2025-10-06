package com.soccer.stats.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TEAMS")
public class Team implements ModelObject {

    @Id
    private String id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "founded_year")
    private Integer foundedYear;

    @Column(name = "president")
    private String president;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "website")
    private String website;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.List<Player> players = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "homeTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.List<Match> homeMatches = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "awayTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.List<Match> awayMatches = new java.util.ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Competition competition;

    @OneToMany(mappedBy = "fromTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.List<Transfer> transfersFrom = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "toTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private java.util.List<Transfer> transfersTo = new java.util.ArrayList<>();

}
