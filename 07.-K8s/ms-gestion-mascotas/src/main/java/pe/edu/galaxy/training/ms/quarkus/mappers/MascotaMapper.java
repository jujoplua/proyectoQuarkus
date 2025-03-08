package pe.edu.galaxy.training.ms.quarkus.mappers;

import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaAddRequest;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaResponse;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;
import java.util.List;

public interface MascotaMapper {

    MascotaEntity toEntity(MascotaAddRequest mascotaAddRequest);

    MascotaResponse toDTO(MascotaEntity mascotaEntity);

    List<MascotaResponse> toDTO(List<MascotaEntity> lstMascotaEntity);

}
