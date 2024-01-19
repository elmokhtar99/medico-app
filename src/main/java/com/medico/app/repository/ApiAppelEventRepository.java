package com.medico.app.repository;

import com.medico.app.domain.ApiAppelEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiAppelEventRepository extends JpaRepository<ApiAppelEvent,Long> {
}
