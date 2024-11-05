package org.myproject.hello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vet_specialities")
@Getter @Setter
public class VetSpeciality {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_specialities_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;
}
