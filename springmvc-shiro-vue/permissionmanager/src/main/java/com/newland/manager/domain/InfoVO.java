package com.newland.manager.domain;

import lombok.Data;

import java.util.List;

@Data
public class InfoVO {
    private String avatar;
    private String name;
    private List<String> roles;
    private List<String> data;
}
