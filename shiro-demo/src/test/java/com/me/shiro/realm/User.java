package com.me.shiro.realm;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String salt;
    private String locked;
    
    public String getCredentialsSalt() {
        return username+salt;
    }
}
