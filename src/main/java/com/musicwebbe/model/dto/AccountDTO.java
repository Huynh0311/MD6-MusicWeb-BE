package com.musicwebbe.model.dto;


import com.musicwebbe.model.Role;

import java.util.Set;

public class AccountDTO {
    private Long id;
    private String name;
    private Role roles;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String name, Role roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
