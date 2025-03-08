package com.proyecto.quarkus.repository;

import com.proyecto.quarkus.model.Libro;
import com.proyecto.quarkus.util.EstadoLibro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LibroRespository implements PanacheRepository<Libro> {
    public List<Libro> findByTitulo(String titulo) {
        return list("titulo", titulo);
    }

    public List<Libro> findByEstaddo(EstadoLibro estado) {
        return list("estado", estado);
    }

}
