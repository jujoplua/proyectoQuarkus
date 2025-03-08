package com.proyecto.quarkus.dto;
import java.io.Serializable;


public record LibroDto(Long id, String titulo, String autor, String isbn, String estado) implements Serializable {
}