package com.proyecto.quarkus.mapper;

import com.proyecto.quarkus.dto.UsuarioRequest;
import com.proyecto.quarkus.model.Usuario;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface UsuarioRequestMapper {
    Usuario toEntity(UsuarioRequest usuarioRequest);

    UsuarioRequest toDto(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioRequest usuarioRequest, @MappingTarget Usuario usuario);
}