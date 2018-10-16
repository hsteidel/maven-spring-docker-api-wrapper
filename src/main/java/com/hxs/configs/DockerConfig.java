package com.hxs.configs;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HSteidel
 *
 *  Configure all things Docker Client
 *
 */
@Configuration
public class DockerConfig {


    /**
     *  Make the Spotify Docker Client a bean
     */
    @Bean
    public DockerClient dockerClient() throws DockerCertificateException {
        return DefaultDockerClient.fromEnv().build();
    }

}
