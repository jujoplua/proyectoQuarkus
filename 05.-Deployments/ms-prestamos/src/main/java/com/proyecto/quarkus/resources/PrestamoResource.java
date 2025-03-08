package com.proyecto.quarkus.resources;

import com.proyecto.quarkus.dto.PrestamoDto;
import com.proyecto.quarkus.dto.PrestamoRequest;
import com.proyecto.quarkus.model.Prestamo;
import com.proyecto.quarkus.service.PrestamoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/prestamos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrestamoResource {

    @Inject
    PrestamoService prestamoService;

    @GET
    public List<PrestamoDto> getAllPrestamos() {
        return prestamoService.getAllPrestamos();
    }

    @POST
    @Transactional
    public Response createPrestamo(PrestamoRequest prestamo) {
        PrestamoDto p = prestamoService.createPrestamo(prestamo);
        if(p != null){
            return Response.status(Response.Status.CREATED).entity(p).build();
        }else
            return Response.status(Response.Status.NOT_FOUND).entity("El libro no está disponible").build();
    }
}
