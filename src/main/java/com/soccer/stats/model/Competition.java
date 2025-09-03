package com.soccer.stats.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Competition implements ModelObject {

    @Id
    private Long id;
    private String name;
    private String country;
    @ManyToOne
    private Season season;
    @ManyToOne
    private Federation federation;
    private boolean international;

}
