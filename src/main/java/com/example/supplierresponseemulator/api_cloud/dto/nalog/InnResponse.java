package com.example.supplierresponseemulator.api_cloud.dto.nalog;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InnResponse {
    private int status;
    private boolean found;
    private String inn;
    private Inquiry inquiry;

    public static InnResponse createInnResponse() {
        return InnResponse.builder()
                .status(200)
                .found(true)
                .inn("123456789012")
                .inquiry(new Inquiry(0.6F, 5418.10, 1, 1))
                .build();
    }
}
