package com.proyecto.quarkus.resources;

import com.proyecto.quarkus.dto.UsuarioDto;
import com.proyecto.quarkus.dto.UsuarioRequest;
import com.proyecto.quarkus.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;


@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    public List<UsuarioDto> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @POST
    @Transactional
    public Response createUsuario(UsuarioRequest user) {
        UsuarioDto u = usuarioService.createUsuario(user);
        return Response.status(Response.Status.CREATED).entity(u).build();
    }

    @GET
    @Path("/{id}")
    public Response getUsuarioById(@PathParam("id") Long id) {
        UsuarioDto user = usuarioService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @GET
    @Path("/search")
    public List<UsuarioDto> getUsuariosByNombre(@QueryParam("name") String name) {
        return usuarioService.findByNombre(name);
    }
}
