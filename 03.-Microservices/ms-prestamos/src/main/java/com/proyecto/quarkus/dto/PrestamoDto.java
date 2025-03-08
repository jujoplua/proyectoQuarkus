package com.proyecto.quarkus.dto;

import com.proyecto.quarkus.model.EstadoPrestamo;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.proyecto.quarkus.model.Prestamo}
 */
public record PrestamoDto(Long id, Date fechaPrestamo, Date fechaVencimiento, EstadoPrestamo estado, UsuarioDto usuario, LibroDto libro) implements Serializable {
}