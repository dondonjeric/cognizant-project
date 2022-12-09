package com.academy.project.dto;

import lombok.Data;

@Data
public class UpdateCommunityAdminAndManagerRest {

    private Long id;
    private String name;
    private Boolean isactive = true;
}
