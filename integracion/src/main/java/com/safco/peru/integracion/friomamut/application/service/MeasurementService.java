package com.safco.peru.integracion.friomamut.application.service;

import com.safco.peru.integracion.friomamut.application.port.in.MeasurementUseCase;
import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.domain.port.out.MeasurementRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementService implements MeasurementUseCase {

    private final MeasurementRepositoryPort  measurementRepositoryPort;

    @Override
    public Flux<Measurement> findAll() {
        return measurementRepositoryPort.findAll();
    }

    @Override
    public Mono<Measurement> findById(Long id) {
        return measurementRepositoryPort
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Measurement not found with id: " + id)));
    }

    @Override
    public Mono<Measurement> save(Measurement measurement) {
        return measurementRepositoryPort.save(measurement);
    }

    @Override
    public Mono<Void> deleteByFechaProceso(LocalDate fechaProceso) {
        return measurementRepositoryPort.deleteByFechaProceso(fechaProceso);
    }

    @Override
    public Flux<Measurement> findByFechaProceso(LocalDate fechaProceso) {
        return measurementRepositoryPort.findByFechaProceso(fechaProceso);
    }

    @Override
    public Mono<Measurement> update(Long id, Measurement measurement) {
        return findById(id)
                .flatMap(existing -> measurementRepositoryPort.update(id, measurement));
    }

    @Override
    public Flux<Measurement> saveAll(Flux<Measurement> measurements) {
        return measurementRepositoryPort.saveAll(measurements);
    }

}
