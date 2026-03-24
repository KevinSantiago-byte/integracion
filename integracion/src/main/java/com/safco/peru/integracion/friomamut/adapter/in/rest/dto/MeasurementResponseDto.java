package com.safco.peru.integracion.friomamut.adapter.in.rest.dto;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MeasurementResponseDto (
        Long id,
        String tipo,
        String zona,
        String proceso,
        LocalDateTime fecha,
        String sensor,
        Float valores,
        LocalDate fechaProceso
) {

    public static MeasurementResponseDto fromDomain(Measurement measurement) {
        return new MeasurementResponseDto(
                measurement.id(),
                measurement.tipo(),
                measurement.zona(),
                measurement.proceso(),
                measurement.fecha(),
                measurement.sensor(),
                measurement.valores(),
                measurement.fechaProceso()
        );
    }
}
