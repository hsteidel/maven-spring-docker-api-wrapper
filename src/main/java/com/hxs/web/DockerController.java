package com.hxs.web;

import com.hxs.service.DockerService;
import com.spotify.docker.client.messages.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HSteidel
 *
 *  Test Controller To Test The Docker Service
 */
@RestController
@RequestMapping("/docker/")
public class DockerController {

    private final Logger logger = LoggerFactory.getLogger(DockerController.class);

    private final DockerService dockerService;

    public DockerController(DockerService dockerService1) {
        this.dockerService = dockerService1;
    }


    @GetMapping("/images")
    @ResponseStatus(HttpStatus.OK)
    public List<Image> getAllImages(){
        logger.debug("Looking for all images....");
        return dockerService.listAllDockerImages();
    }

}
