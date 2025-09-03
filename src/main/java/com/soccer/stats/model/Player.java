package com.soccer.stats.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PLAYERS")
public class Player implements ModelObject {

    @Id
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

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Transfer> transfer = new java.util.ArrayList<>();

}
