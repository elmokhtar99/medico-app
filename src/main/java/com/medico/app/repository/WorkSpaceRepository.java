package com.medico.app.repository;

import com.medico.app.domain.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSpaceRepository extends JpaRepository<WorkSpace,Long> {
}
