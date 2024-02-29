package com.example.supplierresponseemulator.api_cloud.dto.rosfinmon;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RosFinMonPositiveResponse extends Response {
    private int status; // Статус ответа
    private boolean found; // Найдены результаты
    private int count; // Количество записей
    private List<Result> result;
    private Inquiry inquiry;

    public static RosFinMonPositiveResponse createPositiveResponse() {
        return RosFinMonPositiveResponse.builder()
                .status(200)
                .found(true)
                .count(1)
                .result(List.of(Result.createResult()))
                .inquiry(new Inquiry(0.3F, 2768.03, 1, 1))
                .build();
    }
}
