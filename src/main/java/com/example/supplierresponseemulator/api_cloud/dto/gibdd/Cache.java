package com.example.supplierresponseemulator.api_cloud.dto.gibdd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Если вывод результата из кэша
public class Cache {

    private String actual; // Дата актуальности
    private TimeStamp start; // Промежуток начала поиска
    private TimeStamp stop; // Промежуток конца поиска

    public static Cache createCache() {
        return new Cache("16.12.2021 00:04:48",
                new TimeStamp(1639580690L, "15.12.2021 18:04:50"),
                new TimeStamp(1639623890L, "16.12.2021 06:04:50"));
    }
}
