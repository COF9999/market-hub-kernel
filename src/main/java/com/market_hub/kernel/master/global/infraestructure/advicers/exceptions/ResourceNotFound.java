package com.market_hub.kernel.master.global.infraestructure.advicers.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}
