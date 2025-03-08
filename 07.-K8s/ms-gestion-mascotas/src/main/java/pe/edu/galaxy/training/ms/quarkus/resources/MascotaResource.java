package pe.edu.galaxy.training.ms.quarkus.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaAddRequest;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaResponse;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;

import java.util.List;

public interface MascotaResource {

    @GET
    List<MascotaResponse> findAll();

    @GET
    @Path("/{id}")
    MascotaResponse findById(@PathParam("id") Long id);

    @GET
    @Path("/search")
    MascotaResponse search(@QueryParam("nombre") String nombre);

    @GET
    @Path("/search-like")
    List<MascotaResponse> searchLike(@QueryParam("nombre") String nombre);

    @GET
    @Path("/find-by-cliente/{clienteId}")
    List<MascotaResponse> findByCliente(@PathParam("clienteId") Long clienteId);


    @Transactional
    @POST
    void save(MascotaAddRequest mascotaAddRequest);
}
