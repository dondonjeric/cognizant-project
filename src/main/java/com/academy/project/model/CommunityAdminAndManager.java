package com.academy.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "communityadminandmanager")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommunityAdminAndManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, name ="communityadminandmanagername")
    private String name;

    @Column(length = 10, nullable = false, name ="cognizantid")
    private String cognizantId;

    @Column(length = 50, nullable = false, name ="csvemail")
    private String email;

    @Column(length = 15, nullable = true)
    private String password;

    @Column(length = 10, nullable = false, name ="roletype")
    private String roleType;

    @Column(nullable = false)
    private Boolean isactive = true; // design tech lead question
}
