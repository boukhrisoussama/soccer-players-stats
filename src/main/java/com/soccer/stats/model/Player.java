package com.soccer.stats.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PLAYERS")
public class Player implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "position")
    private String position;

    // --- Added statistics fields ---
    @Column(name = "matches_played")
    private int matchesPlayed;

    @Column(name = "goals_scored")
    private int goalsScored;

    @Column(name = "assists_made")
    private int assistsMade;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "red_cards")
    private int redCards;

    @Column(name = "minutes_played")
    private int minutesPlayed;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(optional = true)
    @JsonBackReference
    private Team team;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Transfer> transfers = new ArrayList<>();

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<PlayerMatchStats> matchStats = new ArrayList<>();

}
