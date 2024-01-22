package com.medico.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = Patient.TABLE_NAME)
public class Patient extends BaseEntity{
    public static final String TABLE_NAME = "PATIENT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "PATIENT_id_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "patient")
    private List<CheckUp> checkUps = new ArrayList<>();
}
