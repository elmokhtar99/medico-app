package com.medico.app.repository;

import com.medico.app.domain.Mutual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualRepository extends JpaRepository<Mutual,Long> {
}
