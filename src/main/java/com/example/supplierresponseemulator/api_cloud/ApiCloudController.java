package com.example.supplierresponseemulator.api_cloud;

import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@Validated
//localhost:8080 заменить на api-cloud.ru
public class ApiCloudController {

    // константа - токен - 53ba1b7a55abbа14aa97eff3a5220792

    private final ApiCloudService apiCloudService;

    //ФССП - исполнительное производство
    //http://localhost:8080/api/fssp.php?type=physical&lastname=Иванов&firstname=Иван&secondname=Иванович&birthdate=31.03.1995&region=-1&token=53ba1b7a55abbа14aa97eff3a5220792
    //http://localhost:8080/api/fssp.php?type=physical&lastname=Петров&firstname=Петр&birthdate=15.07.1980&region=-1&token=53ba1b7a55abbа14aa97eff3a5220792
    @GetMapping("/fssp.php")
    public Response getFsspEnfProcessings(@RequestParam(required = false) String type,
                                          @RequestParam(required = false) String lastname,
                                          @RequestParam(required = false) String firstname,
                                          @RequestParam(required = false) String secondname,
                                          @RequestParam(required = false) String birthdate,
                                          @RequestParam(defaultValue = "-1") String region, // Пока регион -1, другие не нужны
                                          @RequestParam(required = false) String token,
                                          @RequestParam(defaultValue = "1") int searchAll, // Пока по умолчанию 4 страницы, нужна хардкодить этот параметр, потому что у поставщика по умолчанию 0 (1 страница)
                                          @RequestParam(defaultValue = "0") int onlyActual) { // TODO проверить на реальном API - какой параметр выводит актуальные,а какой историю.

        return apiCloudService.fsspEnfPrecessing(type, lastname, firstname, secondname, birthdate, region, token, searchAll, onlyActual);
    }

    //ФНС - поиск ИНН по ФИО, паспорту и дате рождения
    // http://localhost:8080/api/nalog.php?type=inn&lastname=Петров&firstname=Петр&birthdate=15.07.1980&serianomer=9876543210&token=53ba1b7a55abbа14aa97eff3a5220792
    @RequestMapping(value = "/nalog.php", method = RequestMethod.GET, params = {"type", "firstname", "lastname", "birthdate", "serianomer", "token"})
    public Response getInn(@RequestParam(required = false) String type,
                           @RequestParam(required = false) String firstname,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) String secondname,
                           @RequestParam(required = false) String birthdate,
                           @RequestParam(required = false) String serianomer,
                           @RequestParam(required = false) String token) {
        return apiCloudService.getInn(type, firstname, lastname, secondname, birthdate, serianomer, token);
    }

    //ФНС - поиск самозанятого по ИНН
    // http://localhost:8080/api/nalog.php?type=npd&inn=123456789012&token=53ba1b7a55abbа14aa97eff3a5220792
    @RequestMapping(value = "/nalog.php", method = RequestMethod.GET, params = {"type", "inn", "token"})
    public Response getSelfEmpl(@RequestParam(required = false) String type,
                                @RequestParam(required = false) String inn,
                                @RequestParam(required = false) String token) {
        return apiCloudService.getSelfEmpl(type, inn, token);
    }

    //МВД - действительность паспорта по серии и номеру
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000000&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 0: не значится среди недействительных
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000001&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 1: значит Не действителен (ИЗЬЯТ, УНИЧТОЖЕН)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000002&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 2: значит Не действителен (ТЕХНИЧЕСКИЙ БРАК)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000003&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 3: значит Не действителен (ЗАМЕНЕН НА НОВЫЙ)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000004&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 4: значит Не действителен (УТРАТА ПАСПОРТА)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000005&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 5: значит Не действителен (В СВЯЗИ СО СМЕРТЬЮ ВЛАДЕЛЬЦА)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000006&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 6: значит Не действителен (ЧИСЛИТСЯ В РОЗЫСКЕ)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000007&token=53ba1b7a55abbа14aa97eff3a5220792 - номер не в диапазоне 000001-000006: рандомно одна из ошибок от поставщика
    @GetMapping("/mvd.php")
    public Response getPassportCheck(@RequestParam(required = false) String type,
                                     @RequestParam(required = false) String seria,
                                     @RequestParam(required = false) String nomer,
                                     @RequestParam(required = false) String token) {
        return apiCloudService.getPassportCheck(type, seria, nomer, token);
    }

    //ГИБДД - проверка водительских прав по серии номеру и дате выдачи, в выдаче есть информация о лишениях.
    //http://localhost:8080/api/gibdd.php?type=driver&serianomer=1234567890&date=07.11.2014&token=53ba1b7a55abbа14aa97eff3a5220792 - на запрос будет получен ответ с данными
    //http://localhost:8080/api/gibdd.php?type=driver&date=07.11.2014&token=53ba1b7a55abbа14aa97eff3a5220792 - на запрос будет получен ответ с ошибкой
    //http://localhost:8080/api/gibdd.php?type=driver&serianomer=3334567890&date=07.11.2014&token=53ba1b7a55abbа14aa97eff3a5220792 - не найдено
    @GetMapping("/gibdd.php")
    public Response getDriverIDCheck(@RequestParam(required = false) String type,
                                     @RequestParam(required = false) String serianomer,
                                     @RequestParam(required = false) String date,
                                     @RequestParam(required = false) String token) {
        return apiCloudService.getDriverIdCheck(type, serianomer, date, token);
    }

    //Росфинмониторинг - информация по террористам/экстремистам
    //http://localhost:8080/api/fedsfm.php?type=terextr&search=АБАКАРОВ ШАМИЛЬ БАГОМЕДОВИЧ&token=53ba1b7a55abbа14aa97eff3a5220792
    @GetMapping("/fedsfm.php")
    public Response getTerExtrCheck(@RequestParam(required = false) String type,
                                    @RequestParam(required = false) String search,
                                    @RequestParam(required = false) String token) {
        return apiCloudService.getTerExtrCheck(type, search, token);
    }

    //Федресурс - банкротства по ИНН
    //http://localhost:8080/api/bankrot.php?type=searchString&string=123456789012&legalStatus=fiz&token=53ba1b7a55abbа14aa97eff3a5220792 - запись найдена
    //http://localhost:8080/api/bankrot.php?type=searchString&string=123456789013&legalStatus=fiz&token=53ba1b7a55abbа14aa97eff3a5220792 - записей не найдено
    @GetMapping("/bankrot.php")
    public Response getBankruptCheck(@RequestParam(required = false) String type,
                                     @RequestParam(required = false) String string,
                                     @RequestParam(required = false) String legalStatus,
                                     @RequestParam(required = false) String token
                                     // Судя по всему параметр нам не нужен
//                                     @RequestParam(required = false) String isActiveLegalCase,
                                     // Судя по всему параметр нам не нужен - Доп информация - место и дата рождения банкрота
                                     // Если значение 1 - то включить, если нет параметра, то без доп информации
//                                     @RequestParam(defaultValue = "1") String dopInfo
                                    ) {
        return apiCloudService.getBankruptCheck(type, string, legalStatus, token);
    }


}
