package com.taotao.cloud.goods.infrastructure.configuration.client;

public class ServiceCallException extends RuntimeException {
    public ServiceCallException(String message) {
        super(message);
    }
}
