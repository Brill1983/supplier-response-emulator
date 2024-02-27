package com.example.supplierresponseemulator.api_cloud;

import com.example.supplierresponseemulator.api_cloud.dto.fssp.FsspResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class ApiCloudController {

    // константа - токен - 53ba1b7a55abbа14aa97eff3a5220792

    private final ApiCloudService apiCloudService;

    //Два успешных запроса в Постмен для получения не нужных данных:
    //http://localhost:8080/api/fssp.php?type=physical&lastname=Иванов&firstname=Иван&secondname=Иванович&birthdate=31.03.1995&region=-1&token=53ba1b7a55abbа14aa97eff3a5220792
    //http://localhost:8080/api/fssp.php?type=physical&lastname=Петров&firstname=Петр&birthdate=15.07.1980&region=-1&token=53ba1b7a55abbа14aa97eff3a5220792
    @GetMapping("/fssp.php")
    // пока с указанием каждого параметра, чтобы легче было отслеживать - в будущем можно заменить на map и добавить проверки в валидаторе.
    public FsspResponse getFsspEnfProcessings(@RequestParam(required = false) String type,
                                              @RequestParam(required = false) String lastname,
                                              @RequestParam(required = false) String firstname,
                                              @RequestParam(required = false) String secondname,
                                              @RequestParam(required = false) String birthdate,
                                              @RequestParam(defaultValue = "-1") String region, // Пока регион -1, другие не нужны
                                              @RequestParam String token,
                                              @RequestParam(defaultValue = "1") int searchAll, // Пока по умолчанию 4 страницы, нужна хардкодить этот параметр, потому что у поставщика по умолчанию 0 (1 страница)
                                              @RequestParam(defaultValue = "0") int onlyActual) { // TODO на реальном API - какой параметр выводит актуальные,а какой историю.

        return apiCloudService.fsspEnfPrecessing(type, lastname, firstname, secondname, birthdate, region, token, searchAll, onlyActual);
    }

}
