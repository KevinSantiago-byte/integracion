package com.safco.peru.integracion.friomamut.adapter.in.rest.dto;


import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MeasurementRequestDto(
        @NotBlank String tipo,
        @NotBlank String zona,
        @NotBlank String proceso,
        @NotNull LocalDateTime fecha,
        @NotBlank String sensor,
        @NotNull  Float valores,
        @NotNull LocalDate fechaProceso
) {

    public Measurement toDomain() {
        return Measurement.create(
                this.tipo,
                this.zona,
                this.proceso,
                this.fecha,
                this.sensor,
                this.valores,
                this.fechaProceso
        );
    }
}
