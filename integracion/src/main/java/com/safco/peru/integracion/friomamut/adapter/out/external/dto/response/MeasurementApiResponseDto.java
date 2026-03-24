package com.safco.peru.integracion.friomamut.adapter.out.external.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record MeasurementApiResponseDto(
        @JsonProperty("Tipo")
        String tipo,
        @JsonProperty("Zona")
        String zona,
        @JsonProperty("Proceso")
        String proceso,
        @JsonProperty("Fecha")
        LocalDateTime fecha,
        @JsonProperty("Sensor")
        String sensor,
        @JsonProperty("Valores")
        Float valores
) { }
