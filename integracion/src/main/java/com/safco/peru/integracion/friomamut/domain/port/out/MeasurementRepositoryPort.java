package com.safco.peru.integracion.friomamut.domain.port.out;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


public interface MeasurementRepositoryPort {

    Mono<Measurement> save(Measurement measurement);
    Mono<Measurement> findById (Long id);
    Flux<Measurement> findAll ();
    Mono<Void> deleteByFechaProceso (LocalDate fechaProceso);
    Flux<Measurement> findByFechaProceso(LocalDate fechaProceso);
    Mono<Measurement> update(Long id, Measurement measurement);
    Flux<Measurement> saveAll(Flux<Measurement> measurements);

}
