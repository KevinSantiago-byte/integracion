package com.safco.peru.integracion.friomamut.adapter.out.persistence.adapter;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.domain.port.out.MeasurementRepositoryPort;
import com.safco.peru.integracion.friomamut.adapter.out.persistence.mapper.MeasurementPersistenceMapper;
import com.safco.peru.integracion.friomamut.adapter.out.persistence.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MeasurementPersistenceAdapter implements MeasurementRepositoryPort {
    private final MeasurementRepository measurementRepository;
    private final MeasurementPersistenceMapper mapper;

    @Override
    public Mono<Measurement> save(Measurement measurement) {
        return measurementRepository.save(mapper.toEntity(measurement))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Measurement> findById(Long id) {
        return measurementRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Measurement> findAll() {
        return measurementRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteByFechaProceso(LocalDate fechaProceso) {
        return measurementRepository.deleteByFechaProceso(fechaProceso);
    }

    @Override
    public Flux<Measurement> findByFechaProceso(LocalDate fechaProceso) {
        return measurementRepository.findByFechaProceso(fechaProceso)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Measurement> update(Long id, Measurement measurement) {
        return measurementRepository.findById(id)
                .flatMap( entity -> {
                        mapper.updateEntityFromDomain(measurement,entity);
                        return measurementRepository.save(entity);
                })
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Measurement> saveAll(Flux<Measurement> measurements) {
        return  measurementRepository.saveAll(measurements.map(mapper::toEntity))
                .map(mapper::toDomain);
    }
}
