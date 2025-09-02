package com.soccer.stats.model.security;

import com.soccer.stats.model.ModelObject;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum UserRole implements GrantedAuthority, ModelObject {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
