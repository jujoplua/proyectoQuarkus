package com.proyecto.quarkus.mapper;

import com.proyecto.quarkus.dto.PrestamoRequest;
import com.proyecto.quarkus.model.Prestamo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface PrestamoRequestMapper {
    Prestamo toEntity(PrestamoRequest prestamoRequest);

    PrestamoRequest toDto(Prestamo prestamo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Prestamo partialUpdate(PrestamoRequest prestamoRequest, @MappingTarget Prestamo prestamo);
}