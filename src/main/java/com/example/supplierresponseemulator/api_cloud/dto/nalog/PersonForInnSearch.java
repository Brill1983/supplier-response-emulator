package com.example.supplierresponseemulator.api_cloud.dto.nalog;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonForInnSearch {
    private String firstname;
    private String lastname;
    private String secondname;
    private String bithdate;
    private String serianomer;

    public static List<PersonForInnSearch> createPersonsForInnSearch() {
        PersonForInnSearch personOne = PersonForInnSearch.builder()
                .firstname("Иван")
                .lastname("Иванов")
                .secondname("Иванович")
                .bithdate("31.03.1995")
                .serianomer("1234567890")
                .build();

        PersonForInnSearch personTwo = PersonForInnSearch.builder()
                .firstname("Петр")
                .lastname("Петров")
                .secondname("Петрович")
                .bithdate("15.07.1980")
                .serianomer("9876543210")
                .build();

        return List.of(personOne, personTwo);
    }
}
