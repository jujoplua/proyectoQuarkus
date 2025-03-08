package com.proyecto.quarkus.mapper;

import com.proyecto.quarkus.dto.LibroDto;
import com.proyecto.quarkus.model.Libro;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface LibroMapper {
    Libro toEntity(LibroDto libroDto);

    LibroDto toDto(Libro libro);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Libro partialUpdate(LibroDto libroDto, @MappingTarget Libro libro);
}