package com.medico.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Prescription.TABLE_NAME)
public class Prescription extends BaseEntity{
    public static final String TABLE_NAME = "PRESCRIPTION";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "PRESCRIPTION_id_seq", allocationSize = 1)
    private Long id;

    @Lob
    private String description;

    @OneToMany(mappedBy = "prescription")
    private List<Storage> files = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mutual_id")
    private Mutual mutual;

    @OneToMany(mappedBy = "prescription")
    private List<MedecineCustom> medicineCustoms = new ArrayList<>();

    @OneToMany(mappedBy = "prescription")
    private List<Medecine> medecines = new ArrayList<>();

    @OneToOne(mappedBy = "prescription")
    @JsonIgnore
    private CheckUp checkUp;


}
