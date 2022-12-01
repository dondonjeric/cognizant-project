package com.academy.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "community")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communityid")
    private Long id;

    @Column(length = 50,name = "communityname")
    private String name;

    @Column(name = "communityicon")
    private String icon;

    @Column(name = "communitymgrid")
    private Long mgrid;

    @Column(length = 240,name = "communitydesc")
    private String desc;

    private Boolean isactive=true;




}
