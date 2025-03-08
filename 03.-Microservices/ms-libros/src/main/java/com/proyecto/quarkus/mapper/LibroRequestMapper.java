package com.proyecto.quarkus.mapper;

import com.proyecto.quarkus.dto.LibroRequest;
import com.proyecto.quarkus.model.Libro;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface LibroRequestMapper {
    Libro toEntity(LibroRequest libroRequest);

    LibroRequest toDto(Libro libro);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Libro partialUpdate(LibroRequest libroRequest, @MappingTarget Libro libro);
}