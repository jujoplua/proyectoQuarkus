package com.proyecto.quarkus.mapper;

import com.proyecto.quarkus.dto.PrestamoDto;
import com.proyecto.quarkus.model.Prestamo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface PrestamoMapper {
    Prestamo toEntity(PrestamoDto prestamoDto);

    PrestamoDto toDto(Prestamo prestamo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Prestamo partialUpdate(PrestamoDto prestamoDto, @MappingTarget Prestamo prestamo);
}