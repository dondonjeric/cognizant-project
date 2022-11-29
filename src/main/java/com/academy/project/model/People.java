package com.academy.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peopleid")
    private Long id;
    @Column(name = "cognizantid")
    private Long cognizantid;
    @Column(length = 50,name = "lastname")
    private String lastname;
    @Column(length = 40,name = "firstname")
    private String firstname;
    @Column(length = 40,name = "middlename")
    private String middlename;
    @Column(length = 130,name = "fullname")
    private String fullname;
    @Column(length = 50,name = "csvemail")
    private String csvemail;
    @Column(name = "hireddate")
    private Date hireddate;
    @Column(name = "communityid")
    private Integer communityid;
    @Column(name = "communityadminandmanagerid")
    private Long communityadminandmanagerid;
    @Column(name = "workstateid")
    private Integer workstateid;
    @Column(name = "joblevelid")
    private Integer joblevelid;
    @Column(name = "projectid")
    private Integer projectid;
    @Column(name = "isprobationary")
    private Boolean isprobationary;
    @Column(name = "isactibe")
    private Boolean isactive;










}
