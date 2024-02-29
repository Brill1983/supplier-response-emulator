package com.example.supplierresponseemulator.api_cloud;

import com.example.supplierresponseemulator.api_cloud.dto.Response;
import com.example.supplierresponseemulator.api_cloud.dto.mvd.MvdResponse;
import com.example.supplierresponseemulator.api_cloud.dto.nalog.SelfEmplResponse;
import jakarta.validation.constraints.Size;
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

    //Два успешных запроса в Постмен для получения не нужных данных:
    //http://localhost:8080/api/fssp.php?type=physical&lastname=Иванов&firstname=Иван&secondname=Иванович&birthdate=31.03.1995&region=-1&token=53ba1b7a55abbа14aa97eff3a5220792
    //http://localhost:8080/api/fssp.php?type=physical&lastname=Петров&firstname=Петр&birthdate=15.07.1980&region=-1&token=53ba1b7a55abbа14aa97eff3a5220792
    @GetMapping("/fssp.php")
    // пока с указанием каждого параметра, чтобы легче было отслеживать - в будущем можно заменить на map и добавить проверки в валидаторе.
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

    // http://localhost:8080/api/nalog.php?type=inn&lastname=Петров&firstname=Петр&birthdate=15.07.1980&serianomer=9876543210&token=53ba1b7a55abbа14aa97eff3a5220792
    @RequestMapping(value = "/nalog.php", method = RequestMethod.GET, params = {"type", "firstname", "lastname", "birthdate", "serianomer", "token"})
    public Response getInn(@RequestParam String type,
                           @RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam(required = false) String secondname,
                           @RequestParam String birthdate,
                           @RequestParam @Size(min = 10, max = 10) String serianomer,
                           @RequestParam String token) {
        return apiCloudService.getInn(type, firstname, lastname, secondname, birthdate, serianomer, token);
    }

    //http://localhost:8080/api/nalog.php?type=npd&inn=123456789012&token=53ba1b7a55abbа14aa97eff3a5220792
    @RequestMapping(value = "/nalog.php", method = RequestMethod.GET, params = {"type", "inn", "token"})
    public Response getSelfEmpl(@RequestParam String type,
                                        @RequestParam @Size(min = 12, max = 12) String inn,
                                        @RequestParam String token) {
        return apiCloudService.getSelfEmpl(type, inn, token);
    }

    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000000&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 0: не значится среди недействительных
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000001&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 1: значит Не действителен (ИЗЬЯТ, УНИЧТОЖЕН)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000002&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 2: значит Не действителен (ТЕХНИЧЕСКИЙ БРАК)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000003&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 3: значит Не действителен (ЗАМЕНЕН НА НОВЫЙ)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000004&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 4: значит Не действителен (УТРАТА ПАСПОРТА)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000005&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 5: значит Не действителен (В СВЯЗИ СО СМЕРТЬЮ ВЛАДЕЛЬЦА)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000006&token=53ba1b7a55abbа14aa97eff3a5220792 - результат 6: значит Не действителен (ЧИСЛИТСЯ В РОЗЫСКЕ)
    //http://localhost:8080/api/mvd.php?type=chekpassport&seria=0000&nomer=000007&token=53ba1b7a55abbа14aa97eff3a5220792 - номер не в диапазоне 000001-000006: рандомно одна из ошибок от поставщика
    @GetMapping("/mvd.php")
    public Response getPassportCheck(@RequestParam String type,
                                        @RequestParam String seria,
                                        @RequestParam String nomer,
                                        @RequestParam String token) {
        return apiCloudService.getPassportCheck(type, seria, nomer, token);
    }

    @GetMapping("/gibdd.php")
    public Response getDriverIDCheck(@RequestParam String serianomer,
                                          @RequestParam String date,
                                          @RequestParam String token) {
        return apiCloudService.getDriverIdCheck(serianomer, date, token);
    }
}
