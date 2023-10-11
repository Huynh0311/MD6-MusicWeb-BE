package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String password;
    @Column(length = 10)
    private String phone;
    @Column(length = 150)
    private String email;
    @Lob
    private String img;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isAuth;
}
