package com.safco.peru.integracion.friomamut.adapter.out.persistence.mapper;

import com.safco.peru.integracion.friomamut.adapter.out.persistence.entity.PalletEntity;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PalletPersistenceMapper {

    PalletEntity toEntity(Pallet pallet);
    Pallet toDomain(PalletEntity palletEntity);

}
