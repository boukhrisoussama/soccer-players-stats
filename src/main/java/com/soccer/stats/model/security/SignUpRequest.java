package com.soccer.stats.model.security;

import com.soccer.stats.model.ModelObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest implements ModelObject {

    String userName;
    String email;
    String firstName;
    String lastName;
    String password;
    LocalDate birthDate;

    Address address;
    List<String> authorities = new ArrayList<>();
}
