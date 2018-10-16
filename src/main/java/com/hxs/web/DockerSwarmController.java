package com.hxs.web;

import com.hxs.service.DockerSwarmService;
import com.hxs.web.model.ServiceReplicaState;
import com.spotify.docker.client.messages.Image;
import com.spotify.docker.client.messages.swarm.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HSteidel
 *
 *  Test Controller to test the DockerSwarmService
 */
@RestController
@RequestMapping("/docker/swarm")
public class DockerSwarmController {

    private final Logger logger = LoggerFactory.getLogger(DockerSwarmController.class);

    private final DockerSwarmService dockerSwarmService;

    public DockerSwarmController( DockerSwarmService dockerService) {
        this.dockerSwarmService = dockerService;
    }


    @GetMapping("/services")
    @ResponseStatus(HttpStatus.OK)
    public List<Service> getAllSwarmServices(){
        return dockerSwarmService.listAllSwarmServices();
    }


    @GetMapping("/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public Service getSwarmService(@PathVariable String serviceId){
        return dockerSwarmService.getServiceByServiceId(serviceId);
    }


    @GetMapping("/services/{serviceId}/replicas")
    @ResponseStatus(HttpStatus.OK)
    public ServiceReplicaState getNumberOfServiceReplicas(@PathVariable String serviceId){
        return new ServiceReplicaState(serviceId, dockerSwarmService.getServiceNumberOfReplicas(serviceId));
    }


    @PutMapping("/services/{serviceId}/replicas/{replicas}")
    @ResponseStatus(HttpStatus.OK)
    public String changeServiceReplicas(@PathVariable String serviceId,@PathVariable Long replicas){
        logger.debug("changing" + serviceId + " to " + replicas + " replicas");
        if(!dockerSwarmService.changeServiceReplicas(serviceId, replicas)){
            throw new RuntimeException("Could not update service replicase, unknown why.");
        }
        return "Success";
    }

}
