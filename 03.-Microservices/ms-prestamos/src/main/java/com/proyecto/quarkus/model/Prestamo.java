package com.proyecto.quarkus.model;

import com.proyecto.quarkus.dto.LibroDto;
import com.proyecto.quarkus.dto.UsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "fecha_prestamo")
    private Date fechaPrestamo;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "fecha_devolucion")
    private Date fechaDevolucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPrestamo estado;

    @Transient
    private UsuarioDto usuario;

    @Transient
    private LibroDto libro;
}
