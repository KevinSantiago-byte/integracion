package com.safco.peru.integracion.friomamut.adapter.out.external.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PalletApiResponseDto(
        @JsonProperty("Fecha") String fecha,
        @JsonProperty("Semana") int semana,
        @JsonProperty("Tunel") int tunel,
        @JsonProperty("Zonificado") String zonificado,
        @JsonProperty("Inicio") String inicio,
        @JsonProperty("Final") String finalizado,
        @JsonProperty("Cambioflujo") String cambioFlujo,
        @JsonProperty("Duracion") String duracion,
        @JsonProperty("Presion") double presion,
        @JsonProperty("Paleta") String paleta,
        @JsonProperty("Embalaje") String embalaje,
        @JsonProperty("Presentacion") String presentacion,
        @JsonProperty("Variedad") String variedad,
        @JsonProperty("Envase") String envase,
        @JsonProperty("Cajas") Integer caja,
        @JsonProperty("Parihuela") String parihuela,
        @JsonProperty("Etiqueta") String etiqueta,
        @JsonProperty("Ubicacion") String ubicacion
) {
}
