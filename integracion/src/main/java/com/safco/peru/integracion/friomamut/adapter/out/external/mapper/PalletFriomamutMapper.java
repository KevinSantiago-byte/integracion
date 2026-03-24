package com.safco.peru.integracion.friomamut.adapter.out.external.mapper;

import com.safco.peru.integracion.friomamut.adapter.out.external.dto.response.PalletApiResponseDto;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PalletFriomamutMapper {

    Pallet toDomain(PalletApiResponseDto palletApiResponseDto);
}
