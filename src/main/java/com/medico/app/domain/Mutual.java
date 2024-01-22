package com.medico.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = Mutual.TABLE_NAME)
public class Mutual extends BaseEntity{
    public static final String TABLE_NAME = "MUTUAL";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "MUTUAL_id_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "mutual")
    private List<File> files = new ArrayList<>();

    @OneToOne(mappedBy = "mutual")
    @JsonIgnore
    private Prescription prescription;

}
