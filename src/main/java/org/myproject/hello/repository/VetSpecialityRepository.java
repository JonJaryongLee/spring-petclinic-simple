package org.myproject.hello.repository;

import org.myproject.hello.entity.VetSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetSpecialityRepository extends JpaRepository<VetSpeciality, Long> {
}
