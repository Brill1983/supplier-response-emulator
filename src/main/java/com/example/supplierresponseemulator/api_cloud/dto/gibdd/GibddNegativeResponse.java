package com.example.supplierresponseemulator.api_cloud.dto.gibdd;

import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GibddNegativeResponse extends Response {
    private int status = 404;
    private String message = "Ресурс не вернул ID запроса"; // Требует уточнения
}
