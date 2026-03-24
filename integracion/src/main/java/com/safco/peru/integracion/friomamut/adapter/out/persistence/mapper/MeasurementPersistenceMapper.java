package com.safco.peru.integracion.friomamut.adapter.out.persistence.mapper;

import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.adapter.out.external.dto.response.MeasurementApiResponseDto;
import com.safco.peru.integracion.friomamut.adapter.out.persistence.entity.MeasurementEntity;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeasurementPersistenceMapper {

    Measurement toDomain(MeasurementEntity measurementEntity);

    MeasurementEntity toEntity(Measurement measurement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(
            Measurement measurement,
            @MappingTarget MeasurementEntity measurementEntity
    );
}
