package com.market_hub.kernel.master.global.infraestructure.advicers.exceptions;

public class ResourceAlreadyExists extends RuntimeException{
    public ResourceAlreadyExists(String message) {
        super(message);
    }
}
