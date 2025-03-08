package pe.edu.galaxy.training.ms.quarkus.services;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaAddRequest;
import pe.edu.galaxy.training.ms.quarkus.dtos.MascotaResponse;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;
import pe.edu.galaxy.training.ms.quarkus.mappers.MascotaMapper;
import pe.edu.galaxy.training.ms.quarkus.repository.MascotaRepository;

import java.util.List;

@ApplicationScoped
public class MascotaServiceImpl implements MascotaService {

    @Inject
    private MascotaMapper mascotaMapper;
    @Inject
    private MascotaRepository mascotaRepository;


    @Override
    public List<MascotaResponse> findAll() {
        return  mascotaMapper.toDTO(mascotaRepository.findAll().list());
    }

    @Override
    public MascotaResponse findById(Long id) {

        MascotaEntity mascotaEntity=mascotaRepository.findById(id);
        return mascotaMapper.toDTO(mascotaEntity);
    }
    @Override
    public MascotaResponse search(String nombre) {

       MascotaEntity mascotaEntities=  mascotaRepository.search(nombre);
       return mascotaMapper.toDTO(mascotaEntities);
    }

    public List<MascotaResponse> searchLike(String nombre){
        List<MascotaEntity> lstMascotaEntities=  mascotaRepository.searchLike(nombre);
        return mascotaMapper.toDTO(lstMascotaEntities);
    }

    @Override
    public List<MascotaResponse> findByCliente(Long clienteId) {
        List<MascotaEntity> lstMascotaEntities=  mascotaRepository.findByCliente(clienteId);
        return mascotaMapper.toDTO(lstMascotaEntities);
    }

    @Override
    public void save(MascotaAddRequest mascotaAddRequest) {
        MascotaEntity mascotaEntity= mascotaMapper.toEntity(mascotaAddRequest);
        mascotaRepository.persist(mascotaEntity);
    }

}
