package com.example.vidT.models;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    USER(10), SUPERUSER(30), ADMIN(1000);
    private int i;
    Role(int i) {
        this.i=i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String getAuthority(){
        return name();
    }
}
