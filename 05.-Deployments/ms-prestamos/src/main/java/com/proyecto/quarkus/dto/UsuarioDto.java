package com.proyecto.quarkus.dto;

import java.io.Serializable;


public record UsuarioDto(Long id, String nombres, String apellidos, String email,
                         String telefono) implements Serializable {
}