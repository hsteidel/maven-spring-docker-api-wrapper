package com.hxs.utils.docker;

import com.spotify.docker.client.messages.swarm.Service;
import com.spotify.docker.client.messages.swarm.ServiceMode;
import com.spotify.docker.client.messages.swarm.ServiceSpec;

/**
 * @author HSteidel
 *
 *      Quirky that the ServiceSpecs have to be rebuilt when you update a service...
 *      but if you don't, Docker will complain that you are trying to change things up on it or that you haven't supplied enough info
 *
 *      So this utility class attempts to hide all that from the Service layer to make it a little cleaner
 *
 */
public class ServiceSpecUtils {


    public static ServiceSpec updateServiceReplicas(Service service, Long replicas){
        return ServiceSpec.builder()
                .name(service.spec().name())
                .taskTemplate(service.spec().taskTemplate())
                .mode(ServiceMode.withReplicas(replicas))
                .endpointSpec(service.spec().endpointSpec())
                .updateConfig(service.spec().updateConfig())
                .build();
    }

}
