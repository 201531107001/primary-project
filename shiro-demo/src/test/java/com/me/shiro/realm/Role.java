package com.me.shiro.realm;

import lombok.Data;

@Data
public class Role {
    private Long id;
    private String permission;
    private String description;
    private int available;
}
