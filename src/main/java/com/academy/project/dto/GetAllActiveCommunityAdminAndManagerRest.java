package com.academy.project.dto;

import lombok.Data;

@Data
public class GetAllActiveCommunityAdminAndManagerRest {
    private Long id;
    private String name;
    private String cognizantId;
    private String email;
    private String roleType;
}
