package com.safco.peru.integracion.friomamut.adapter.out.external.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record FriomamutRequestDto(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate desde,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate hasta
) {

    public static FriomamutRequestDto fetchByDateRange(LocalDate startDate, LocalDate endDate) {
        return new FriomamutRequestDto(startDate, endDate);
    }
}
