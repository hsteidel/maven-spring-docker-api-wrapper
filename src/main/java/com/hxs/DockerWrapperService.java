package com.hxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *    This application simply wraps the Docker API with a custom Rest API.
 *
 *    I just wanted to play with the Spotify Docker Client.
 *
 *
 * @author HSteidel
 */
@SpringBootApplication
public class DockerWrapperService {

    public static void main(String[] args) {
        SpringApplication.run(DockerWrapperService.class, args);
    }

}
