package pe.edu.galaxy.training.ms.quarkus.registers;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ConsulRegister {

    @ConfigProperty(name = "consul.host", defaultValue = "")  String host;
    @ConfigProperty(name = "consul.port", defaultValue = "8500") int port;

    @ConfigProperty(name = "quarkus.http.port", defaultValue = "7081") int msPort;
    @ConfigProperty(name = "quarkus.application.name", defaultValue = "ms-gestion-mascotas") String msName;

    public void init(@Observes StartupEvent ev, Vertx vertx) {
        ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions().setHost(host).setPort(port));
        client.registerService(new ServiceOptions().setPort(msPort).setAddress(host).setName(msName).setId(msName+'-'+port));
    }
}
