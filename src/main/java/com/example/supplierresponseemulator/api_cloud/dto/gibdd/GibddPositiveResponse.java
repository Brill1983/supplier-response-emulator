package com.example.supplierresponseemulator.api_cloud.dto.gibdd;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GibddPositiveResponse extends Response {
    private Doc doc;
    private List<Deprivation> decis; // Ифнормаци о лишениях
    private int status; // Статус ответа
    private boolean found; // Найдено удостоверение или нет, true - найдено | false - не найдено
    private Inquiry inquiry; // Информация о запросе
    private Cache cache; // Если вывод результата из кэша

    public static Response createGibddResponse() {
        return GibddPositiveResponse.builder()
                .doc(Doc.createDoc())
                .decis(List.of(Deprivation.createDeprivation()))
                .status(200)
                .found(true)
                .inquiry(new Inquiry(0.5F, 538.78, 1, 1))
                .cache(Cache.createCache())
                .build();
    }
}
