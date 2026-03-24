package com.safco.peru.integracion.friomamut.application.port.in;

import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PalletUseCase {
    Mono<Pallet> save(Pallet pallet);
    Flux<Pallet> saveAll(Flux<Pallet> pallets);
    Mono<Void> deleteByFechaProceso(String fechaProceso);
}
