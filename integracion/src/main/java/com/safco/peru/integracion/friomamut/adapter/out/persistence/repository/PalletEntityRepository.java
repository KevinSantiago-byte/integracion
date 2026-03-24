package com.safco.peru.integracion.friomamut.adapter.out.persistence.repository;

import com.safco.peru.integracion.friomamut.adapter.out.persistence.entity.PalletEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface PalletEntityRepository extends ReactiveCrudRepository<PalletEntity, Long> {
    Mono<Void> deleteByFechaProceso(LocalDate fechaProceso);
}
