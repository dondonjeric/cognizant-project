package com.academy.project.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CommunityAdminAndManagerDTO {
    private Long id;
    private String name;
    private String cognizantId;
    private String email;
    private String password;
    private String roleType;

    public CommunityAdminAndManagerDTO(String name, String cognizantId, String email, String password, String roleType) {
        this.name = name;
        this.cognizantId = cognizantId;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }
}
