package com.proyecto.quarkus.balancer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import io.smallrye.stork.api.LoadBalancer;
import io.smallrye.stork.api.NoServiceInstanceFoundException;
import io.smallrye.stork.api.ServiceInstance;
import org.jboss.logging.Logger;

public class AcmeLoadBalancer implements LoadBalancer {

    private final Random random;

    private static final Logger LOG = Logger.getLogger(AcmeLoadBalancer.class);

    public AcmeLoadBalancer(AcmeLoadBalancerConfiguration config) {
        random = new Random();
    }

    @Override
    public ServiceInstance selectServiceInstance(Collection<ServiceInstance> serviceInstances) {
        if (serviceInstances.isEmpty()) {
            throw new NoServiceInstanceFoundException("No services found.");
        }
        int index = random.nextInt(serviceInstances.size());
        ServiceInstance selected = new ArrayList<>(serviceInstances).get(index);
        LOG.info("Instancia seleccionada:" + selected.getHost() + "    " + selected.getPort());
        return selected;
    }

    @Override
    public boolean requiresStrictRecording() {
        return false;
    }
}
