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

}
