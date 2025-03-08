package com.proyecto.quarkus.repository;

import com.proyecto.quarkus.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public List<Usuario> findByNombres(String nombre) {
        return list("nombres", nombre);
    }
}
