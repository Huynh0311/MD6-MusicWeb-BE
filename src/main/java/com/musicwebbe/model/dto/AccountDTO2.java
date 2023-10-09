package com.musicwebbe.model.dto;

import com.musicwebbe.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO2 {
    private int id;
    private String name;
    private String email;
    private String img;
    private Role role;
}
