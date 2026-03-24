package com.safco.peru.integracion.friomamut.domain.port.in;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface ExternalFriomamutApiPort {

    Flux<Measurement> fetchMeasurementsByDateRange(LocalDate startDate, LocalDate endDate);
    Flux<Pallet> fetchPalletsByDateRange(LocalDate startDate, LocalDate endDate);

}
