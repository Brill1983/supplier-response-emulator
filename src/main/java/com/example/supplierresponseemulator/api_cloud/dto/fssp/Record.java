package com.example.supplierresponseemulator.api_cloud.dto.fssp;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Record {

    private String debtor_name; // ФИО должника
    private String debtor_address; // Место рождения
    private String debtor_dob; // Дата рождения
    private String process_title; // Номер исполнительного производства
    private String process_date; // Дата начала делопроизводства
    private String recIspDoc; // Реквизиты исполнительного документа (вид, дата принятия органом, номер, наименование органа, выдавшего исполнительный документ)
    private String stopIP; // Окончание делопроизводства
    private String subject; // Вид взыскания (Предмет исполнения)
    private String sum; // Сумма непогашенной задолженности
    private String document_organization; // Отдел судебных приставов
    private String document_type; // Предмет исполнения, сумма непогашенной задолженности
    private String officer_name; // Пристав
    // Контакты пристава
    private List<List<String>> officer_phones; // Список со списком телефонов
    // Информация о запросе
    private Inquiry inquiry;

    public static List<Record> createRecords() {

        Record firstRecord = Record.builder()
                .debtor_name("Иванов Иван Иванович") // точный порядок вывода неизвестен
                .debtor_address("РОССИЯ, МОСКВА, УЛ. ВАВИЛОВА...")
                .debtor_dob("31.03.1995")
                .process_title("197****/21/*****-ИП")
                .process_date("26.11.2021")
                .recIspDoc("Судебный приказ от 27.07.2021 № 2А-1****/**** СУДЕБНЫЙ УЧАСТОК № **** *********** ВНУТРИГОРОДСКОГО ОКРУГА Г. *******")
                .stopIP("")
                .subject("Взыскание налогов и сборов, включая пени (кроме таможенных)")
                .sum("142.12")
                .document_organization("ОСП по ************* г. **** *****, г. *************")
                .document_type("Взыскание налогов и сборов, включая пени (кроме таможенных): 142.12 руб. ")
                .officer_name("Околотков О. О.")
                .officer_phones(List.of(List.of("+786********7")))
                .inquiry(new Inquiry(0.8F, 10462.11, 1, 1))
                .build();

        Record secondRecord = Record.builder()
                .debtor_name("Петров Петр Петрович") // точный порядок вывода неизвестен
                .debtor_address("РОССИЯ, САНКТ-ПЕТЕРБУРГ, ГРАЖДАНСКИЙ ПР....")
                .debtor_dob("15.07.1980")
                .process_title("165****/13/*****-ИП")
                .process_date("11.02.2022")
                .recIspDoc("Судебный приказ от 12.03.2022 № 3А-2****/**** СУДЕБНЫЙ УЧАСТОК № **** *********** ОКРУГА Г. *******")
                .stopIP("")
                .subject("Взыскание налогов и сборов, включая пени (кроме таможенных)")
                .sum("3570.65")
                .document_organization("ОСП по ************* г. **** *****, г. *************")
                .document_type("Взыскание налогов и сборов, включая пени (кроме таможенных): 3570.65 руб. ")
                .officer_name("Исполнитель З. К.")
                .officer_phones(List.of(List.of("+794********3")))
                .inquiry(new Inquiry(0.8F, 9613.83, 1, 1))
                .build();

        return List.of(firstRecord, secondRecord);
    }
}
