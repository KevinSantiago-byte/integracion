package com.safco.peru.integracion.friomamut.application.port.out;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface SyncFriomamutUseCase {
    Flux<Measurement> syncMeasurementsByDateRange(LocalDate startDate, LocalDate endDate);
    Flux<Pallet> syncPalletsByDateRange(LocalDate startDate, LocalDate endDate);
}
