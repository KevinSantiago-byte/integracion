package com.safco.peru.integracion.friomamut.adapter.out.persistence.repository;

import com.safco.peru.integracion.friomamut.adapter.out.persistence.entity.MeasurementEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface MeasurementRepository extends ReactiveCrudRepository<MeasurementEntity,Long> {

    Flux<MeasurementEntity> findByFechaProceso(LocalDate fechaProceso);
    Mono<Void> deleteByFechaProceso(LocalDate fechaProceso);

}
