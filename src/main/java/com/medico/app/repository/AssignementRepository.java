package com.medico.app.repository;

import com.medico.app.domain.Assignement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignementRepository extends JpaRepository<Assignement,Long> {
}
