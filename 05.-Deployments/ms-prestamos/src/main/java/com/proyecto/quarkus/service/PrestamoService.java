package com.proyecto.quarkus.service;

import com.proyecto.quarkus.dto.LibroDto;
import com.proyecto.quarkus.dto.PrestamoDto;
import com.proyecto.quarkus.dto.PrestamoRequest;
import com.proyecto.quarkus.integrations.LibroClient;
import com.proyecto.quarkus.integrations.UsuarioClient;
import com.proyecto.quarkus.mapper.PrestamoMapper;
import com.proyecto.quarkus.mapper.PrestamoRequestMapper;
import com.proyecto.quarkus.model.EstadoPrestamo;
import com.proyecto.quarkus.model.Prestamo;
import com.proyecto.quarkus.repository.PrestamoRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@ApplicationScoped
public class PrestamoService {
    @Inject
    PrestamoRepository prestamoRepository;

    @Inject
    PrestamoRequestMapper prestamoRequestMapper;

    @Inject
    PrestamoMapper  prestamoMapper;

    @Inject
    @RestClient
    UsuarioClient usuarioClient;

    @Inject
    @RestClient
    LibroClient libroClient;

    public List<PrestamoDto> getAllPrestamos() {
        List<Prestamo> prestamos = prestamoRepository.listAll();
        return prestamos.stream()
                .map(this::enrichPrestamo)
                .map(prestamoMapper::toDto)
                .collect(Collectors.toList());
    }

    private Prestamo enrichPrestamo(Prestamo prestamo) {
        prestamo.setUsuario(usuarioClient.getUsuarioById(prestamo.getIdUsuario()));
        prestamo.setLibro(libroClient.getLibroById(prestamo.getIdLibro()));
        return prestamo;
    }

    public PrestamoDto createPrestamo(PrestamoRequest prestamo) {

        LocalDate start = LocalDate.of(2025, Month.MARCH, 1);
        LocalDate end = LocalDate.of(2025, Month.DECEMBER, 31);
        LocalDate random = this.between(start, end);

        LibroDto libroDto = libroClient.getLibroById(prestamo.getIdLibro());
        if(libroDto != null){
            Log.info(libroDto.toString());
            if(!libroDto.estado().equalsIgnoreCase("DISPONIBLE")){
                return null;
            }
        }

        Prestamo p = prestamoRequestMapper.toEntity(prestamo);
        p.setEstado(EstadoPrestamo.ACTIVO);
        p.setFechaVencimiento(this.convertToDateViaInstant(random));
        p.setFechaDevolucion(null);
        p.setUsuario(usuarioClient.getUsuarioById(prestamo.getIdUsuario()));


        LibroDto updatedBook = new LibroDto(libroDto.id(), libroDto.titulo(), libroDto.autor(), libroDto.isbn(), "PRESTADO");
        p.setLibro(libroDto);

        libroClient.updateBook(prestamo.getIdLibro(),updatedBook);

        prestamoRepository.persist(p);
        return prestamoMapper.toDto(p);
    }

    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
