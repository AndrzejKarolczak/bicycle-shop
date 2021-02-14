package com.example.bicycleshop.security;

public enum Roles {
    ADMINISTRATOR(1L), EMPLOYEE(2L), CLIENT(3L);
    
    private final Long authorityGroupNumber;
    
    Roles(long authorityGroupNumber) {
        this.authorityGroupNumber = authorityGroupNumber;
    }
    
    public Long getAuthorityGroupNumber() {
        return authorityGroupNumber;
    }
}
