package com.proyecto.quarkus.integrations;

import com.proyecto.quarkus.dto.UsuarioDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/usuarios")
@RegisterRestClient(configKey="usuarios-api")
public interface UsuarioClient {

    @GET
    @Path("/{id}")
    @CircuitBreaker(
            failOn = RuntimeException.class,
            delay = 10000,
            requestVolumeThreshold = 4,
            failureRatio = 0.5
    )
    @Retry(maxRetries = 1)
    @Fallback(fallbackMethod = "fallbackGetUsuasrioById")
    UsuarioDto getUsuarioById(@PathParam("id") Long id);

    // Método de fallback (se ejecuta si todos los reintentos fallan)
    default UsuarioDto fallbackGetUsuasrioById(Long id) {
        return null;
    }

}
