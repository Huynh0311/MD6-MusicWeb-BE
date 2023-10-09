package com.musicwebbe.model.dto;

import com.musicwebbe.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingerDTO {
    private int id;
    private String[] nameSinger;
    private Account account;
}

