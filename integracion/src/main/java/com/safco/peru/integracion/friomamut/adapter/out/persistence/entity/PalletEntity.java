package com.safco.peru.integracion.friomamut.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "produccion", name = "api_pallet")
public class PalletEntity {

    @Id
    private Long id;
    private LocalDate fecha;
    private Integer semana;
    private Integer tunel;
    private String zonificado;
    private String inicio;
    private String finalizado;
    @Column("cambio_flujo")
    private String cambioFlujo;
    private String duracion;
    private Double presion;
    private String paleta;
    private String embalaje;
    private String presentacion;
    private String variedad;
    private String envase;
    private Integer caja;
    private String parihuela;
    private String etiqueta;
    private String ubicacion;
    @Column("fecha_proceso")
    private LocalDate fechaProceso;
}
