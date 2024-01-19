package com.medico.app.domain;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import static com.medico.app.Constant.API_DATE_TIME_FORMAT;

@Entity
@Table(name = "API_APPEL_EVENT")
@Data
public class ApiAppelEvent extends BaseEntity {

    public static final String TABLE_NAME = "API_APPEL_EVENT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "API_APPEL_EVENT_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "DEMANDE_UID")
    private String demandeUid;

    @Column(name = "APPEL_ADRESSE")
    private String adresse;

    @Column(name = "APPEL_USER")
    private String user;

    @JsonFormat(pattern = API_DATE_TIME_FORMAT)
    @Column(name = "DATE_DEBUT_APPEL")
    private LocalDateTime dateDebut;

    @JsonFormat(pattern = API_DATE_TIME_FORMAT)
    @Column(name = "DATE_FIN_APPEL")
    private LocalDateTime dateFin;

    @Column(name = "ERR_CODE")
    private String errCode;

    @Lob
    @Column(name = "ERR_TRACE")
    private String errTrace;


    @Override
    public Long getId() {
        return null;
    }
}
