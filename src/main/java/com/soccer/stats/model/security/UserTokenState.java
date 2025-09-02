package com.soccer.stats.model.security;

import com.soccer.stats.model.ModelObject;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenState implements ModelObject {

    private String accessToken;
    private Long expiresIn;

}
