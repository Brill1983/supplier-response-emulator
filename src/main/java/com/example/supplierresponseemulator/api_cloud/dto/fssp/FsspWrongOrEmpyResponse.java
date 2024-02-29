package com.example.supplierresponseemulator.api_cloud.dto.fssp;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FsspWrongOrEmpyResponse extends Response {
    private int status;
    private int count;
    private String message;
    private Inquiry inquiry;

    public static Response createWrongOrEmptyResponse() {
        return new FsspWrongOrEmpyResponse(200, 0, "В базе ФССП отсутствует",
                new Inquiry(0.8F, 10362.11, 1, 1));
    }
}
