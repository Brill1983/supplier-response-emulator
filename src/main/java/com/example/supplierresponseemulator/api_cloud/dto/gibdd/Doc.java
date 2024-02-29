package com.example.supplierresponseemulator.api_cloud.dto.gibdd;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import static com.example.supplierresponseemulator.api_cloud.utils.Constants.DATE_FORMAT;

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

    public static Doc createDoc() {

        return Doc.builder()
                .division("МРЭО ГИБДД Г. ИЗОБИЛЬНЫЙ ГУ МВД РОССИИ ПО СК")
                .date("2014-11-07")
                .stag("1992")
                .bdate("1974-10-01")
                .num("1234567890")
                .cat("В,В1,С,С1,СЕ,С1Е")
                .type("45")
                .srok("2025-10-07")
                .divid("050 ")
                .build();
    }
}
