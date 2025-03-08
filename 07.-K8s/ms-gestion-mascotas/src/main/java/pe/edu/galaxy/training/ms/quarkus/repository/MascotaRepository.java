package pe.edu.galaxy.training.ms.quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.galaxy.training.ms.quarkus.entities.MascotaEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MascotaRepository implements PanacheRepository<MascotaEntity> {

    public MascotaEntity search(String nombre){
        return  this.find("nombre=:nombre",Parameters.with("nombre", nombre)).firstResult();
    }

    public List<MascotaEntity> searchLike(String nombre) {
        String query = "nombre like :nombre";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombre", "%" + nombre + "%");
        List<MascotaEntity> list = this.find(query, parameters).list();
        return list;
    }

    public List<MascotaEntity> findByCliente(Long clienteId) {
        return  this.find("clienteId=:clienteId",Parameters.with("clienteId", clienteId)).list();
    }
}
