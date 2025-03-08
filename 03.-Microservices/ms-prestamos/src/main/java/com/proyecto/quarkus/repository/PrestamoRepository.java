package com.proyecto.quarkus.repository;

import com.proyecto.quarkus.model.EstadoPrestamo;
import com.proyecto.quarkus.model.Prestamo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PrestamoRepository implements PanacheRepository<Prestamo> {

    public List<Prestamo> findByUserId(Long idUsuario) {
        return list("idUsuario", idUsuario);
    }

    public List<Prestamo> findByStatus(EstadoPrestamo estado) {
        return list("estado", estado);
    }



}
