package com.safco.peru.integracion.friomamut.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "measurements", schema = "produccion")
public class MeasurementEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("tipo")
    private String tipo;

    @Column("zona")
    private String zona;

    @Column("proceso")
    private String proceso;

    @Column("fecha")
    private LocalDateTime fecha;

    @Column("sensor")
    private String sensor;

    @Column("valores")
    private Float valores;

    @Column("fecha_proceso")
    private LocalDate fechaProceso;
}
