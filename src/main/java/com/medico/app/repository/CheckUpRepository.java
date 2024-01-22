package com.medico.app.repository;

import com.medico.app.domain.CheckUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckUpRepository extends JpaRepository<CheckUp,Long> {
}
