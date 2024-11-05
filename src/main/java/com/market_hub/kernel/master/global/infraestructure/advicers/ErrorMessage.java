package com.market_hub.kernel.master.global.infraestructure.advicers;

import lombok.Data;

@Data
public class ErrorMessage {
    private String exception;
    private String message;
    private Integer statusCode;

    public ErrorMessage(Exception exception, Integer statusCode){
        this.exception = exception.getClass().getName();
        this.message = exception.getMessage();
        this.statusCode = statusCode;
    }
}
