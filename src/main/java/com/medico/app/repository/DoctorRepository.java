package com.medico.app.repository;

import com.medico.app.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DoctorRepository extends JpaRepository<Doctor, Long> {


}
