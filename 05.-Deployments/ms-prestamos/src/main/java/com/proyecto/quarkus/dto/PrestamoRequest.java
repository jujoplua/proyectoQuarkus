package com.proyecto.quarkus.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.proyecto.quarkus.model.Prestamo}
 */
@Value
public class PrestamoRequest implements Serializable {
    Long idUsuario;
    Long idLibro;
    Date fechaPrestamo;
}