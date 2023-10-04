package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;

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
    @Lob
    private String img;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
