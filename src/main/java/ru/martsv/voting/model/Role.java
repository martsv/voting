package ru.martsv.voting.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * mart
 * 27.08.2016
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
