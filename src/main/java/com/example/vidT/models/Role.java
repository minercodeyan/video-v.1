package com.example.vidT.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

public enum Role implements GrantedAuthority {
    USER,ADMIN;
    @Override
    public String getAuthority(){
        return null;
    }
}
