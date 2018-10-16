package com.hxs.exceptions;

/**
 * @author HSteidel
 *
 *  Runtime exception to wrap/rethrow all the Docker Client checked exceptions
 *
 */
public class DockerRuntimeException extends RuntimeException {

    public DockerRuntimeException(String message) {
        super(message);
    }

    public DockerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DockerRuntimeException(Throwable e) {
        super(e);
    }
}
