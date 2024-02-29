package com.example.supplierresponseemulator.api_cloud.dto.rosfinmon;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private String id; // id в базе
    private String type; // тип (fiz - физическое лицо, ur - юридическое лицо)
    private String name; // ФИО
    private String birth; // Дата рождения
    private String place; // Место рождения


    public static Result createResult() {
        return Result.builder()
                .id("22")
                .type("fiz")
                .name("АБАКАРОВ ШАМИЛЬ БАГОМЕДОВИЧ*")
                .birth("16.05.1991")
                .place("П. ЗАТЕРЕЧНЫЙ НЕФТЕКУМСКОГО РАЙОНА СТАВРОПОЛЬСКОГО КРАЯ")
                .build();
    }
}
