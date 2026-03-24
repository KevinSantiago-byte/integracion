package com.safco.peru.integracion.friomamut.domain.port.out;

import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PalletRepositoryPort {
    Mono<Pallet> save(Pallet pallet);
    Flux<Pallet> saveAll(Flux<Pallet> pallets);
    Mono<Void> deleteByFechaProceso(LocalDate fechaProceso);
}
