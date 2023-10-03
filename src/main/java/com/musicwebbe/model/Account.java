package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String img;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role;
}
