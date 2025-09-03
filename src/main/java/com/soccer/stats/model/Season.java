package com.soccer.stats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "SEASONS")
public class Season implements ModelObject {

    @Id
    private int year;
    private String name;
    private boolean isCurrent;
    private boolean isCompleted;

}
