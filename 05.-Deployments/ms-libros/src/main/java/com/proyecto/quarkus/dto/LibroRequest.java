package com.proyecto.quarkus.dto;

import com.proyecto.quarkus.util.EstadoLibro;

import java.io.Serializable;

/**
 * DTO for {@link com.proyecto.quarkus.model.Libro}
 */
public record LibroRequest(String titulo, String autor, String isbn, EstadoLibro estado) implements Serializable {
}