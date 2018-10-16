package com.hxs.service;

import com.hxs.exceptions.DockerRuntimeException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author HSteidel
 *
 *  Docker Service to handle all non-swarm activities
 *
 */
@org.springframework.stereotype.Service
public class DockerService {

    private final Logger logger = LoggerFactory.getLogger(DockerService.class);

    private final DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    /* List all local images */
    public List<Image> listAllDockerImages(){

        List<Image> images;
        try {
            images = dockerClient.listImages(DockerClient.ListImagesParam.allImages());
        } catch (DockerException | InterruptedException e) {
            throw new DockerRuntimeException(e);
        }

        return images;
    }
}
