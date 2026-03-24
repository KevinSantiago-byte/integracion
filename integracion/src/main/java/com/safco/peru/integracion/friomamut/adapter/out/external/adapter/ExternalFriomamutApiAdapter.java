package com.safco.peru.integracion.friomamut.adapter.out.external.adapter;

import com.safco.peru.integracion.friomamut.adapter.out.external.mapper.MeasurementFriomamutMapper;
import com.safco.peru.integracion.friomamut.adapter.out.external.mapper.PalletFriomamutMapper;
import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import com.safco.peru.integracion.friomamut.domain.port.in.ExternalFriomamutApiPort;
import com.safco.peru.integracion.friomamut.adapter.out.external.client.FriomamutApiClient;
import com.safco.peru.integracion.friomamut.adapter.out.external.dto.request.FriomamutRequestDto;
import com.safco.peru.integracion.friomamut.adapter.out.persistence.mapper.MeasurementPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalFriomamutApiAdapter implements ExternalFriomamutApiPort {

    private final MeasurementFriomamutMapper measurementMapper;
    private final PalletFriomamutMapper palletMapper;
    private final FriomamutApiClient apiClient;

    @Override
    public Flux<Measurement> fetchMeasurementsByDateRange(LocalDate startDate, LocalDate endDate) {
        return apiClient.fetchMeasurements(buildRequestDto(startDate,endDate))
                .map(measurementMapper::toDomain)
                .doOnError(error -> log.error("Error while fetching measurements", error));
    }

    @Override
    public Flux<Pallet> fetchPalletsByDateRange(LocalDate startDate, LocalDate endDate) {
        return apiClient.fetchPallets(buildRequestDto(startDate,endDate))
                .map(palletMapper::toDomain)
                .doOnError(error -> log.error("Error while fetching pallets", error));
    }

    private FriomamutRequestDto buildRequestDto(LocalDate startDate, LocalDate endDate) {
        return FriomamutRequestDto.fetchByDateRange(startDate, endDate);
    }
}
