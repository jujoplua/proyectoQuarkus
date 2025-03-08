package pe.edu.galaxy.training.ms.quarkus.dtos;

import static java.util.Objects.isNull;

public class MascotaResponse{

    private Long id;

    private String nombre;

    private Float edad;

    private Long clienteId;

    public MascotaResponse(Long id, String nombre, Float edad,Long clienteId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.clienteId=clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        if (isNull(nombre)){
            return "";
        }
        return nombre.toUpperCase();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getEdad() {
        return Float.valueOf(Math.round(edad));
    }

    public void setEdad(Float edad) {
        this.edad = edad;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
