package com.safco.peru.integracion.friomamut.adapter.out.external.mapper;

import com.safco.peru.integracion.friomamut.adapter.out.external.dto.response.MeasurementApiResponseDto;
import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeasurementFriomamutMapper {
    Measurement toDomain(MeasurementApiResponseDto responseDto);
}
