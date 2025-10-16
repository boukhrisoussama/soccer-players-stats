package com.soccer.stats.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "coach")
    private String coach;

    @OneToMany(mappedBy = "team")
    @JsonIgnore // Prevent infinite recursion
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "homeTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion
    private List<Match> homeMatches = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion
    private List<Match> awayMatches = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    @JsonBackReference
    @JsonIgnore // Prevent infinite recursion
    private Competition competition;

    @OneToMany(mappedBy = "fromTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion
    private List<Transfer> transfersFrom = new ArrayList<>();

    @OneToMany(mappedBy = "toTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion
    private List<Transfer> transfersTo = new ArrayList<>();

}
