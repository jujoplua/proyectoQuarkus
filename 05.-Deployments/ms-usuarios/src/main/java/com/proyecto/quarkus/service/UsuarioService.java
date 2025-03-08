package com.proyecto.quarkus.service;

import com.proyecto.quarkus.dto.UsuarioDto;
import com.proyecto.quarkus.dto.UsuarioRequest;
import com.proyecto.quarkus.mapper.UsuarioMapper;
import com.proyecto.quarkus.mapper.UsuarioRequestMapper;
import com.proyecto.quarkus.model.Usuario;
import com.proyecto.quarkus.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    UsuarioRequestMapper usuarioRequestMapper;

    public List<UsuarioDto> getAllUsuarios() {
        return usuarioRepository.listAll()
                .stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    public UsuarioDto createUsuario(UsuarioRequest user) {
        Usuario u = usuarioRequestMapper.toEntity(user);
        u.setFechaCreacion(new Date());
        usuarioRepository.persist(u);
        return usuarioMapper.toDto(u);
    }

    public  UsuarioDto findById(Long id){
        return usuarioMapper.toDto(usuarioRepository.findById(id));
    }

    public List<UsuarioDto> findByNombre(String nombre){
        return usuarioRepository.findByNombres(nombre)
                .stream()
                .map(usuarioMapper::toDto)
                .toList();
    }
}
