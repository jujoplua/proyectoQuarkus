package com.proyecto.quarkus.service;

import com.proyecto.quarkus.dto.LibroDto;
import com.proyecto.quarkus.dto.LibroRequest;
import com.proyecto.quarkus.mapper.LibroMapper;
import com.proyecto.quarkus.mapper.LibroRequestMapper;
import com.proyecto.quarkus.model.Libro;
import com.proyecto.quarkus.repository.LibroRespository;
import com.proyecto.quarkus.util.EstadoLibro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class LibroService {

    @Inject
    LibroRespository libroRespository;

    @Inject
    LibroMapper libroMapper;

    @Inject
    LibroRequestMapper libroRequestMapper;

    public List<LibroDto> getAllLibros() {
        return libroRespository.listAll()
                .stream()
                .map(libroMapper::toDto)
                .toList();
    }

    public List<LibroDto> getLibrosPorEstado(EstadoLibro estado) {
        return libroRespository.findByEstaddo(estado)
                .stream()
                .map(libroMapper::toDto)
                .toList();
    }

    public LibroDto createLibro(LibroRequest book) {
        Libro l = libroRequestMapper.toEntity(book);
        l.setEstado(EstadoLibro.DISPONIBLE);
        l.setFechaCreacion(new Date());
        libroRespository.persist(l);
        return libroMapper.toDto(l);
    }

    public LibroDto updateLibro(Long id, LibroRequest updatedLibro) {
        Libro libro = libroRespository.findById(id);
        if (libro != null) {
            libro.setId(id);
            libro.setTitulo(updatedLibro.titulo());
            libro.setAutor(updatedLibro.autor());
            libro.setIsbn(updatedLibro.isbn());
            libro.setEstado(updatedLibro.estado());
            libroRespository.persist(libro);
        }
        return libroMapper.toDto(libro);
    }

    public  LibroDto findById(Long id){
        return libroMapper.toDto(libroRespository.findById(id));
    }

    public List<LibroDto> findByTitulo(String titulo) {
        return libroRespository.findByTitulo(titulo)
                .stream()
                .map(libroMapper::toDto)
                .toList();
    }
}
