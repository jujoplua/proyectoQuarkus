package pe.edu.galaxy.training.ms.quarkus.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaAddRequest;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaResponse;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;

import java.util.List;

@ApplicationScoped
public class MascotaMapperImpl implements  MascotaMapper{
    @Override
    public MascotaEntity toEntity(MascotaAddRequest mascotaAddRequest) {
        MascotaEntity mascotaEntity = new MascotaEntity();
        mascotaEntity.setNombre(mascotaAddRequest.nombre());
        mascotaEntity.setEdad(mascotaAddRequest.edad());
        return mascotaEntity;
    }

    @Override
    public MascotaResponse toDTO(MascotaEntity mascotaEntity) {
        return new MascotaResponse(
                mascotaEntity.getId(),
                mascotaEntity.getNombre(),
                mascotaEntity.getEdad(),
                mascotaEntity.getClienteId()
        );
    }

    @Override
    public List<MascotaResponse> toDTO(List<MascotaEntity> lstMascotaEntity) {
        return lstMascotaEntity.stream().map(this::toDTO).toList();
    }
}
