package com.musicwebbe.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterRequest {
//    @NotNull
//    @Min(2)
//    @Max(150)
    private String name;
//    @NotNull
//    @Email
    private String email;
//    @NotNull
//    @Pattern()
    private String password;
    private String img;
//    @NotNull
//    @Pattern()
    private String phone;
}
