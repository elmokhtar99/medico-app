package com.medico.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Assignement.TABLE_NAME)
public class Assignement extends BaseEntity{
    public static final String TABLE_NAME = "ASSIGNEMENT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "ASSIGNEMENT_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "DATE_DEBUT", columnDefinition = "TIMESTAMP")
    private LocalDate dateDebut;

    @Column(name = "DATE_FIN", columnDefinition = "TIMESTAMP")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private WorkSpace workspace;

    @OneToMany(mappedBy = "assignement")
    private List<CheckUp> checkUps = new ArrayList<>();
}
