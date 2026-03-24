package com.safco.peru.integracion.friomamut.application.port.in;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface MeasurementUseCase {

    Flux<Measurement>  findAll();
    Mono<Measurement> findById(Long id);
    Mono<Measurement> save(Measurement measurement);
    Mono<Void> deleteByFechaProceso (LocalDate fechaProceso);
    Flux<Measurement> findByFechaProceso(LocalDate fechaProceso);
    Mono<Measurement> update(Long id, Measurement measurement);
    Flux<Measurement> saveAll(Flux<Measurement> measurements);

}
