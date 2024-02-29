package com.example.supplierresponseemulator.api_cloud.dto;

import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeMaxConErrorResponse extends Response {
    private int status = 404;
    private String error = "TIME_MAX_CONNECT";
    private String errormsg = "Достигнуто максимальное количество коннектов, при которых ресурс не вернул результата";
}
