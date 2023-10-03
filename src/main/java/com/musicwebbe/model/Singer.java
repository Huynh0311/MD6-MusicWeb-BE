package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameSinger;
    @ManyToOne
    private Account account;
}
