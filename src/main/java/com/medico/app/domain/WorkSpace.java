package com.medico.app.domain;

import com.medico.app.enums.TypeWorkSpace;
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
@Table(name = WorkSpace.TABLE_NAME)
public class WorkSpace extends BaseEntity{
    public static final String TABLE_NAME = "WORKSPACE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = TABLE_NAME+"_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_WORKSPACE")
    private TypeWorkSpace typeWorkSpace;

    @OneToMany(mappedBy = "workspace")
    private List<Assignement> assignements = new ArrayList<>();
}
