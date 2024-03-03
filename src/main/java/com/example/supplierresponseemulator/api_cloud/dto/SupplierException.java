package com.example.supplierresponseemulator.api_cloud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierException extends Response {

    private String error;
    private String message;
    public SupplierException(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
