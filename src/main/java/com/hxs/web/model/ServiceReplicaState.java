package com.hxs.web.model;

/**
 * @author HSteidel
 */
public class ServiceReplicaState {

    private String serviceId;

    private Long replicas;

    public ServiceReplicaState() {
    }

    public ServiceReplicaState(String serviceId, Long replicas) {
        this.serviceId = serviceId;
        this.replicas = replicas;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Long getReplicas() {
        return replicas;
    }

    public void setReplicas(Long replicas) {
        this.replicas = replicas;
    }
}
