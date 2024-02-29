package com.example.supplierresponseemulator.api_cloud.dto.nalog;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PositiveResponse extends Response {
    private int status;
    private boolean found;
    private String inn;
    private Inquiry inquiry;

    public static PositiveResponse createInnResponse() {
        return PositiveResponse.builder()
                .status(200)
                .found(true)
                .inn("1234567890**")
                .inquiry(new Inquiry(0.6F, 5418.10, 1, 1))
                .build();
    }
}
