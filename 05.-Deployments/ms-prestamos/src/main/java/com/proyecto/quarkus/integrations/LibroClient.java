package com.proyecto.quarkus.integrations;

import com.proyecto.quarkus.dto.LibroDto;
import com.proyecto.quarkus.dto.UsuarioDto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/libros")
//@RegisterRestClient(baseUri = "stork://ms-libros")
@RegisterRestClient(configKey="libros-api")
public interface LibroClient {

    @GET
    @Path("/{id}")
    @CircuitBreaker(
            failOn = RuntimeException.class,
            delay = 10000,
            requestVolumeThreshold = 4,
            failureRatio = 0.5
    )
    @Retry(maxRetries = 1)
    @Fallback(fallbackMethod = "fallbackGetLibroById")
    LibroDto getLibroById(@PathParam("id") Long id);

    // Método de fallback (se ejecuta si todos los reintentos fallan)
    default LibroDto fallbackGetLibroById(Long id) {
        return null;
    }

    @PUT
    @Path("/{id}")
    Response updateBook(@PathParam("id") Long id, LibroDto updatedBook);
}
