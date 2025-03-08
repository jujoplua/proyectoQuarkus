package pe.edu.galaxy.training.ms.quarkus.resources;

import io.quarkus.panache.common.Parameters;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaAddRequest;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaResponse;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;
import pe.edu.galaxy.training.ms.quarkus.services.MascotaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/v1/mascotas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaResourceImpl implements MascotaResource {

    @Inject
    private MascotaService mascotaService;

    @Override
    public List<MascotaResponse> findAll() {
        return mascotaService.findAll();
    }

    @Override
    public MascotaResponse findById(@PathParam("id") Long id) {
        return mascotaService.findById(id);
    }

    @Override
    public MascotaResponse search(@QueryParam("nombre") String nombre) {
        return mascotaService.search(nombre);
    }

    @Override
    public List<MascotaResponse> searchLike(String nombre) {
        return mascotaService.searchLike(nombre);
    }

    @Override
    public List<MascotaResponse> findByCliente(Long clienteId) {
        return mascotaService.findByCliente(clienteId);
    }


    @Override
    public void save(MascotaAddRequest mascotaAddRequest) {
        mascotaService.save(mascotaAddRequest);
    }
}
