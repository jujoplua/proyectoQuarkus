package com.proyecto.quarkus.resources;

import com.proyecto.quarkus.dto.LibroDto;
import com.proyecto.quarkus.dto.LibroRequest;
import com.proyecto.quarkus.service.LibroService;
import com.proyecto.quarkus.util.EstadoLibro;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/libros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibroResource {
    @Inject
    LibroService libroService;

    @GET
    public List<LibroDto> getAllLibros() {
        return libroService.getAllLibros();
    }

    @GET
    @Path("/estado")
    public List<LibroDto> getAllBooks(@QueryParam("estado") EstadoLibro estado) {
        return libroService.getLibrosPorEstado(estado);
    }

    @POST
    @Transactional
    public Response createLibro(LibroRequest libro) {
        LibroDto l = libroService.createLibro(libro);
        return Response.status(Response.Status.CREATED).entity(l).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, LibroRequest updatedBook) {
        LibroDto l = libroService.updateLibro(id, updatedBook);
        if (l == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(l).build();
    }

    @GET
    @Path("/{id}")
    public Response getLibroById(@PathParam("id") Long id) {
        LibroDto l = libroService.findById(id);
        if (l == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(l).build();
    }

    @GET
    @Path("/search")
    public List<LibroDto> getUsersByName(@QueryParam("titulo") String titulo) {
        return libroService.findByTitulo(titulo);
    }
}
