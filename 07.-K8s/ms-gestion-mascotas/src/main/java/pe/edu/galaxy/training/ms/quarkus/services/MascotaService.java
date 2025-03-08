package pe.edu.galaxy.training.ms.quarkus.services;

import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaAddRequest;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaResponse;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;

import java.util.List;

public interface MascotaService {

    List<MascotaResponse> findAll();

    MascotaResponse findById(Long id);

    MascotaResponse search(String nombre);

    List<MascotaResponse> searchLike(String nombre);

    List<MascotaResponse> findByCliente(Long clienteId);

    void save(MascotaAddRequest mascotaAddRequest);
}
