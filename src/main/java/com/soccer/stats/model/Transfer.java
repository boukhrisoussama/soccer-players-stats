package com.soccer.stats.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TRANSFERS")
public class Transfer implements ModelObject {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_team_id")
    @JsonBackReference
    private Team fromTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_team_id")
    @JsonBackReference
    private Team toTeam;

    @Column(name = "date")
    private LocalDate transferDate;

    @Column(name = "fee")
    private BigDecimal transferFee;

    @Column(name = "contract_years")
    private Integer contractYears;
}
