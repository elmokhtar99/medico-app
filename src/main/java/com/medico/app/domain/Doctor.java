package com.medico.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = Doctor.TABLE_NAME)
public class Doctor extends BaseEntity {
    public static final String TABLE_NAME = "DOCTOR";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "DOCTOR_id_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "doctor")
    private List<Assignement> assignements = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "occupation_id")
    private Occupation occupation;


}
