package com.soccer.stats.model.security;

import com.soccer.stats.model.ModelObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements ModelObject {

    String emailIdentifier;
    String appPassword;
}
