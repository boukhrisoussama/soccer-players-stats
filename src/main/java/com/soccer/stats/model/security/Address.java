package com.soccer.stats.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soccer.stats.model.ModelObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "USER_ADDRESS")
@SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
public class Address implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "town")
    private String town;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private UserInformation userInformation;

}
