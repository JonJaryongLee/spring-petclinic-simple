package org.myproject.hello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "owners")
@Getter @Setter
public class Owner extends Person {

    private String address;

    private String city;

    private String telephone;
}
