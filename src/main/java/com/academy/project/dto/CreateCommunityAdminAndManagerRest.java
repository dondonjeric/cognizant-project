package com.academy.project.dto;

import lombok.Data;

@Data
public class CreateCommunityAdminAndManagerRest {

    private Long id;
    private String name;
    private String cognizantId;
    private String email;
    private String password;
    private String roleType;

    public CreateCommunityAdminAndManagerRest(String name, String cognizantId, String email, String password, String roleType) {
        this.name = name;
        this.cognizantId = cognizantId;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }
}
