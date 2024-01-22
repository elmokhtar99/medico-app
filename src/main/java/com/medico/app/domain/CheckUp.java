package com.medico.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = CheckUp.TABLE_NAME)
public class CheckUp extends BaseEntity{
    public static final String TABLE_NAME = "MEDICAL_CHECK_UP";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "MEDICAL_CHECK_UP_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "CHECK_UP_DATE")
    private LocalDateTime checkUpDate;

    @ManyToOne
    @JoinColumn(name = "assignement_id")
    private Assignement assignement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
