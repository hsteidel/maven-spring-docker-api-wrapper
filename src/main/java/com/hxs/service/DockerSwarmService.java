package com.hxs.service;

import com.hxs.exceptions.DockerRuntimeException;
import com.hxs.utils.docker.ServiceSpecUtils;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.swarm.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * @author HSteidel
 *
 *  Docker Swarm Service to handle all things Docker Swarm;
 *
 */
//Prefer import here due to heavy usage of Spotify's Docker Service
@org.springframework.stereotype.Service
public class DockerSwarmService {

    private final Logger logger = LoggerFactory.getLogger(DockerSwarmService.class);

    private final DockerClient dockerClient;

    @Autowired
    public DockerSwarmService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }


    /* list all services */
    public List<Service> listAllSwarmServices(){
        List<Service> services = null;

        try {
           services = dockerClient.listServices();
        } catch (DockerException | InterruptedException e) {
            throw new DockerRuntimeException(e);
        }

        return services;
    }


    /* Get a service by its Id */
    public Service getServiceByServiceId(String serviceId){
        Service service;
        try {
            service = dockerClient.inspectService(serviceId);
        } catch (DockerException | InterruptedException e) {
            throw new DockerRuntimeException(e);
        }

        if(service == null){
            throw new RuntimeException("Service not found with id:" + serviceId);
        }
        logger.debug("Found service: " + serviceId + " " + service.spec().name());
        return service;
    }


    /* Update the number of replicas for a service; up or down */
    public boolean changeServiceReplicas(String serviceId, long replicas){
        Service service = getServiceByServiceId(serviceId);

        try {
            dockerClient.updateService(service.id(), service.version().index(), ServiceSpecUtils.updateServiceReplicas(service, replicas));
        } catch (DockerException | InterruptedException e) {
            throw new DockerRuntimeException(e);
        }

        return true;
    }

    public Long getServiceNumberOfReplicas(String serviceId){
        return getServiceByServiceId(serviceId).spec().mode().replicated().replicas();
    }



}
