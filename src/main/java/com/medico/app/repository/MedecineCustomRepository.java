package com.medico.app.repository;

import com.medico.app.domain.MedecineCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecineCustomRepository extends JpaRepository<MedecineCustom,Long> {
}
