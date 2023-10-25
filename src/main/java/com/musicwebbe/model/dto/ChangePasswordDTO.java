package com.musicwebbe.model.dto;

import com.musicwebbe.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    private Integer id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String oldPassword;
    @Column(length = 50)
    private String password;
    @Column(length = 10)
    private String phone;
    @Column(length = 255)
    private String email;
    @Lob
    private String img;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isAuth;
}
