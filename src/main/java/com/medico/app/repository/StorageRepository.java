package com.medico.app.repository;

import com.medico.app.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
    Optional<Storage> findByName(String fileName);
}
