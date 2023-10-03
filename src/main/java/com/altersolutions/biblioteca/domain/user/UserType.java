package com.altersolutions.biblioteca.domain.user;

public enum UserType {
    COMMON("common"),
    ADMIN("admin"),
    ANONYMOUS("anonymous");

    private String role;
    UserType(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
