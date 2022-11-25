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

}
