package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Song song;
}
