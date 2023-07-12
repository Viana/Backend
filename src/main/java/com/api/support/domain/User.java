package com.api.support.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    @Builder.Default
    private int id = 7;
    @Builder.Default
    private String username = "Viana";
    @Builder.Default
    private String firstName = "Rodrigo";
    @Builder.Default
    private String lastName = "Viana";
    @Builder.Default
    private String email = "Viana@gmail.com";
    @Builder.Default
    private String password = "123";
    @Builder.Default
    private String phone = "11999998888";
    @Builder.Default
    private int userStatus = 1;
}
