package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Account account;
    @ManyToOne
    @JoinColumn(name="song_id", nullable=false)
    private Song song;
    @Lob
    private String content;
    private LocalDate timeComment;
}
