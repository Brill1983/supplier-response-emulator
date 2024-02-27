package com.example.supplierresponseemulator.api_cloud.dto.fssp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FsspWrongOrEmpyResponse extends FsspResponse{
    private int status;
    private int count;
    private String message;
    private Inquiry inquiry;

    public static FsspResponse createWrongOrEmptyResponse() {
        return new FsspWrongOrEmpyResponse(200, 0, "В базе ФССП отсутствует",
                new Inquiry(0.8F, 10362.11, 1, 1));
    }
}
