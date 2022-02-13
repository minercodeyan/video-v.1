package com.example.vidT.models;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    USER,ADMIN;
    @Override
    public String getAuthority(){
        return name();
    }
}
