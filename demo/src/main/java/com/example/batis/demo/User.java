package com.example.batis.demo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User {
    private int uid;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private int defaultMode;
}
