package com.proyecto.quarkus.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.proyecto.quarkus.model.Usuario}
 */
public record UsuarioRequest(String nombres, String apellidos, String email, String telefono) implements Serializable {
}