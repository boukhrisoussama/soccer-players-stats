package com.soccer.stats.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "COMPETITIONS")
public class Competition implements ModelObject {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "federation_id", referencedColumnName = "id")
    private Federation federation;

    // @Column(name = "country")
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CompetitionType type;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<Team> teams;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Match> matches;
}
