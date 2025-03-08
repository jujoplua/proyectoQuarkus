package com.proyecto.quarkus.balancer;

import io.smallrye.stork.api.LoadBalancer;
import io.smallrye.stork.api.ServiceDiscovery;
import io.smallrye.stork.api.config.LoadBalancerType;
import io.smallrye.stork.spi.LoadBalancerProvider;
import jakarta.enterprise.context.ApplicationScoped;

@LoadBalancerType("acme-load-balancer")
@ApplicationScoped
public class AcmeLoadBalancerProvider implements
        LoadBalancerProvider<AcmeLoadBalancerConfiguration> {

    @Override
    public LoadBalancer createLoadBalancer(AcmeLoadBalancerConfiguration config,
                                           ServiceDiscovery serviceDiscovery) {
        return new AcmeLoadBalancer(config);
    }
}
