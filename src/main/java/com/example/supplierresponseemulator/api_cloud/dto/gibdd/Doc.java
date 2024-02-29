package com.example.supplierresponseemulator.api_cloud.dto.gibdd;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Doc {

    private String division; // Кем выдано
    private String date; // Дата выдачи
    private String stag; // Стаж с
    private String bdate; // Дата рождения
    private String num; // Серия и номер
    private String cat; // Категории ТС
    private String type;
    private String srok; // Срок действия
    private String divid;

    public static Doc createDoc(String num, String date) {
        return Doc.builder()
                .divid("МРЭО ГИБДД Г. ИЗОБИЛЬНЫЙ ГУ МВД РОССИИ ПО СК")
                .date(date)
                .stag("1992")
                .bdate("1974-10-01")
                .num(num)
                .cat("В,В1,С,С1,СЕ,С1Е")
                .type("45")
                .srok("2025-10-07")
                .divid("050 ")
                .build();
    }
}
