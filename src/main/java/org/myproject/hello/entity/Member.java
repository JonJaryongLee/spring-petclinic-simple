package org.myproject.hello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "username")
    private String username;

    private String password;

    private Boolean enabled;
}
