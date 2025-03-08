package com.proyecto.quarkus.registers;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ConsulRegister {

    private static final Logger LOG = Logger.getLogger(ConsulRegister.class);


    @ConfigProperty(name = "consul.host", defaultValue = "localhost")  String consulHost;
    @ConfigProperty(name = "consul.port", defaultValue = "8500") int consulPort;
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8201") int msPort;
    @ConfigProperty(name = "quarkus.application.name", defaultValue = "ms-usuarios") String msName;

    public void init(@Observes StartupEvent ev, Vertx vertx) {

        // Obtiene el nombre del contenedor asignado por Docker
        msName = System.getenv("HOSTNAME");
        if (msName == null || msName.isBlank()) {
            msName = "ms-usuarios"; // Fallback en caso de error
        }

        String serviceId = msName + '-' + msPort; // Para que sea único por instancia
        ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions().setHost(consulHost).setPort(consulPort));
        LOG.info(serviceId);
        // Agregar un Health Check
        CheckOptions checkOptions = new CheckOptions()
                .setHttp("http://"+msName+":" + msPort + "/q/health/live")  // Endpoint de salud de Quarkus
                .setInterval("3s")   // Verifica cada 3s
                .setStatus(CheckStatus.PASSING)
                .setDeregisterAfter("5s");  // Elimina la instancia si sigue caída 5s

        ServiceOptions serviceOptions = new ServiceOptions()
                .setPort(msPort)
                .setAddress(msName)  // Usa el hostname asignado por Docker
                .setName("ms-usuarios")
                .setId(serviceId)
                .setCheckOptions(checkOptions);

        client.registerService(serviceOptions);

        //client.registerService(new ServiceOptions().setPort(msPort).setAddress(consulHost).setName(msName).setId(serviceId).setCheckOptions(checkOptions));
    }
}
