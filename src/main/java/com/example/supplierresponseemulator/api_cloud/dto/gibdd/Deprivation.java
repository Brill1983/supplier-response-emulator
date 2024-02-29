package com.example.supplierresponseemulator.api_cloud.dto.gibdd;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Deprivation {

    private String date; // Дата вынесения постановления
    private String fis_id;
    private String bplace;
    private String comment; // Комментарий ГИБДД
    private String reg_name; // Место рождения нарушителя
    private String state; // Состояние исполнения постановления
    private int srok; // Срок лишения (в месяцах)
    private String reg_code;
    private String stateinfo; //    // Состояние исполнения постановления (расшифровка)

    public static Deprivation createDeprivation() {
        return Deprivation.builder()
                .date("2011-06-30")
                .fis_id("26#110720000000007306")
                .bplace("СВЕРДЛОВСКАЯ ОБЛАСТЬ")
                .comment("ЛИШЕНИЕ")
                .reg_name("СТАВРОПОЛЬСКИЙ КРАЙ")
                .state("78")
                .srok(16)
                .reg_code("1107")
                .stateinfo("Начато исчисление срока лишения права управления")
                .build();
    }
}
