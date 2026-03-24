package com.safco.peru.integracion.friomamut.adapter.out.persistence.adapter;

import com.safco.peru.integracion.friomamut.adapter.out.persistence.mapper.PalletPersistenceMapper;
import com.safco.peru.integracion.friomamut.adapter.out.persistence.repository.PalletEntityRepository;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import com.safco.peru.integracion.friomamut.domain.port.out.PalletRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PalletPersistenceAdapter implements PalletRepositoryPort {

    private final PalletEntityRepository palletEntityRepository;
    private final PalletPersistenceMapper palletPersistenceMapper;

    @Override
    public Mono<Pallet> save(Pallet pallet) {
        return palletEntityRepository.save(palletPersistenceMapper.toEntity(pallet))
                .map(palletPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Pallet> saveAll(Flux<Pallet> pallets) {
        return pallets
                .map(palletPersistenceMapper::toEntity)
                .as(palletEntityRepository::saveAll)
                .map(palletPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteByFechaProceso(LocalDate fechaProceso) {
        return palletEntityRepository.deleteByFechaProceso(fechaProceso);
    }

}
