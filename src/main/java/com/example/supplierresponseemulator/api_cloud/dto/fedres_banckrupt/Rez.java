package com.example.supplierresponseemulator.api_cloud.dto.fedres_banckrupt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Rez {
    private Guid guid;
    private Fio fio;
    private Inn inn;
    private Snils snils;
    private Category category;
    private Region region;
    private ArbitrManagerFio arbitrManagerFio;
    private Address address;
    private LastLegalCasenNumber lastLegalCasenNumber;
    private Status status;
    private Description description;
    private UpdateDate updateDate;
    private IsActive isActive;

    public static Rez createRez() {
        return Rez.builder()
                .guid(new Guid("Идентификатор (guid) для поиска по методу search", "62e3d5dc-0000-40e7-0000-f4a912ad78f4"))
                .fio(new Fio("ФИО", "Иванов Иван Иванович"))
                .inn(new Inn("ИНН", "123456789012"))
                .snils(new Snils("СНИЛС", "13700003966"))
                .category(new Category("Категория", "Физическое лицо"))
                .region(new Region("Регион", "Ульяновская область"))
                .arbitrManagerFio(new ArbitrManagerFio("Арбитражный управляющий", "Мнеян Эрмине Арменаковна"))
                .address(new Address("Адрес", "432012, г. Ульяновск, пр-т. проспектов, д. 1а, кв. 0"))
                .lastLegalCasenNumber(new LastLegalCasenNumber("Номер судебного дела", "А72-00000\\/0000"))
                .status(new Status("Статус", "Производство по делу завершено"))
                .description(new Description("Описание", "Производство по делу завершено"))
                .updateDate(new UpdateDate("Последнее обновление", "2022-06-06T00:01:01.247"))
                .isActive(new IsActive("Статус дела (true - в производстве, false - завершено)", "false"))
                .build();
    }
    @AllArgsConstructor
    @Getter
    public static class Guid {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Fio {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Inn {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Snils {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Category {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Region {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class ArbitrManagerFio {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Address {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class LastLegalCasenNumber {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Status {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class Description {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class UpdateDate {
        private String title;
        private String value;
    }

    @AllArgsConstructor
    @Getter
    public static class IsActive {
        private String title;
        private String value;
    }
}

