package org.myproject.hello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vets")
@Getter @Setter
public class Vet extends Person {
}
