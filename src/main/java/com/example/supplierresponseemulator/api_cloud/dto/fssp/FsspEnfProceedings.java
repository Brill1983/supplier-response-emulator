package com.example.supplierresponseemulator.api_cloud.dto.fssp;

import lombok.*;

import java.util.List;

// Данные по исполнительным производствам
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FsspEnfProceedings extends FsspResponse {

    private int status; // Статус запрос
    private String countAll; // Всего записей
    private String pagesAll; // Всего страниц найдено
    private int count; // Количество записей в результате
    private int totalLoadedPage; // Загружено страниц в результате (по умолчанию загружается 1 страница, возможно загрузить максимум 4 страницы при переданном параметре searchAll=1)
    private int onlyActual; // Активность фильтра "Только актуальные делопроизводства"
    private List<Record> records;

    public static FsspResponse createFullResponse(List<Record> records) {
        return FsspEnfProceedings.builder()
                .status(200)
                .countAll("53")
                .pagesAll("3")
                .count(1)
                .totalLoadedPage(3)
                .onlyActual(0)
                .records(records)
                .build();
    }
}
