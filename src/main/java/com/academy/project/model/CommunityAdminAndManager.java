package com.academy.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "communityadminandmanager")
@Data
public class CommunityAdminAndManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String communityadminandmanagername;

    @Column(length = 50, nullable = false)
    private String csvemail;

    @Column(length = 15, nullable = true)
    private String password;

    @Column(length = 10, nullable = false)
    private String cognizantid;

    @Column(length = 10, nullable = false)
    private String roletype;

    @Column(nullable = false)
    private Boolean isactive;
}
