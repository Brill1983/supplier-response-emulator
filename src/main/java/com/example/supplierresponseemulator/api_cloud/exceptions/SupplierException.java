package com.example.supplierresponseemulator.api_cloud.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierException extends RuntimeException {

    private String error;
    private String message;
    public SupplierException(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
