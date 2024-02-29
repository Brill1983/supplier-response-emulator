package com.example.supplierresponseemulator.api_cloud;

import com.example.supplierresponseemulator.api_cloud.dto.Response;
import com.example.supplierresponseemulator.api_cloud.dto.TimeMaxConErrorResponse;
import com.example.supplierresponseemulator.api_cloud.dto.fssp.FsspEnfProceedings;
import com.example.supplierresponseemulator.api_cloud.dto.fssp.FsspWrongOrEmpyResponse;
import com.example.supplierresponseemulator.api_cloud.dto.fssp.Record;
import com.example.supplierresponseemulator.api_cloud.dto.gibdd.GibddNegativeResponse;
import com.example.supplierresponseemulator.api_cloud.dto.gibdd.GibddPositiveResponse;
import com.example.supplierresponseemulator.api_cloud.dto.mvd.MvdResponse;
import com.example.supplierresponseemulator.api_cloud.dto.nalog.NegativeResponse;
import com.example.supplierresponseemulator.api_cloud.dto.nalog.PersonForInnSearch;
import com.example.supplierresponseemulator.api_cloud.dto.nalog.PositiveResponse;
import com.example.supplierresponseemulator.api_cloud.dto.nalog.SelfEmplResponse;
import com.example.supplierresponseemulator.api_cloud.dto.rosfinmon.RosFinMonNegativeResponse;
import com.example.supplierresponseemulator.api_cloud.dto.rosfinmon.RosFinMonPositiveResponse;
import com.example.supplierresponseemulator.api_cloud.exceptions.SupplierException;
import com.example.supplierresponseemulator.api_cloud.utils.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiCloudService {

    private final Validator validator = new Validator();

    public Response fsspEnfPrecessing(String type, String lastname, String firstname, String secondname,
                                      String birthdate, String region, String token, int searchAll, int onlyActual) {
        if (!validator.validFsspReqParams(type, lastname, firstname, birthdate, region, token, searchAll, onlyActual)) {
            return FsspWrongOrEmpyResponse.createWrongOrEmptyResponse(); // TODO переделать под вывод ошибки - так лучше для отладки.
        }

        List<Record> recordList = Record.createRecords();
        String fullName = StringUtils.isBlank(secondname) ? lastname + " " + firstname : lastname + " " + firstname + " " + secondname;
        List<Record> recordsForResponse = new ArrayList<>();

        for (Record record : recordList) {
            if (record.getDebtor_name().contains(fullName) && record.getDebtor_dob().equals(birthdate)) {
                recordsForResponse.add(record);
            }
        }
        if (recordsForResponse.isEmpty()) {
            return FsspWrongOrEmpyResponse.createWrongOrEmptyResponse();
        }
        return FsspEnfProceedings.createFullResponse(recordsForResponse);
    }

    public Response getInn(String type, String firstname, String lastname, String secondname, String birthdate, String serianomer, String token) {
        if (!validator.validInnReqParams(type, firstname, lastname, birthdate, serianomer, token)) {
            return new NegativeResponse();
        }

        // Внимание! Имитация ответа, если база ФНС недоступна, примерно в каждом 4 запросе.
        int randomInt = (int) (Math.random() * 4);
        if (randomInt == 3) {
            throw new SupplierException("404", "Сервис ФНС временно не доступен или ведутся технические работы");
        }
        // Внимание! Имитация ответа примерно в каждом 4 запросе, если достигнуто максимальное количество попыток отправить запрос в ФНС.
        if (randomInt == 2) {
            return new TimeMaxConErrorResponse();
        }

        List<PersonForInnSearch> persons = PersonForInnSearch.createPersonsForInnSearch();
        for (PersonForInnSearch person : persons) {
            if (person.getFirstname().equals(firstname) &&
                    person.getLastname().equals(lastname) &&
                    person.getBithdate().equals(birthdate) &&
                    person.getSerianomer().equals(serianomer)) {
                if (StringUtils.isBlank(secondname) || person.getSecondname().equals(secondname)) {
                    return PositiveResponse.createInnResponse();
                }
            }
        }

        return new NegativeResponse();
    }

    public Response getSelfEmpl(String type, String inn, String token) {
        validator.validToken(token);
        validator.validType(type, "npd");
        validator.validParam(inn);

        // Внимание! Имитация ответа примерно в каждом 4 запросе, если достигнуто максимальное количество попыток отправить запрос в ФНС.
        int randomInt = (int) (Math.random() * 4);
        if (randomInt == 2) {
            return new TimeMaxConErrorResponse();
        }
        // необходимо выяснить, что будет если направить неправильный ИНН - например недостаточно цифр.
        if (inn.equals("123456789012")) {
            return SelfEmplResponse.createSelfEmplResponse(inn, true);
        }
        return SelfEmplResponse.createSelfEmplResponse(inn, false);
    }

    public Response getPassportCheck(String type, String seria, String nomer, String token) {
        validator.validToken(token);
        validator.validType(type, "chekpassport");
        validator.validParam(seria);
        validator.validParam(nomer);

        // Внимание! Имитация ответа примерно в каждом 4 запросе, если достигнуто максимальное количество попыток отправить запрос в ФНС.
        int randomInt = (int) (Math.random() * 4);
        if (randomInt == 2) {
            return new TimeMaxConErrorResponse();
        }
        // Внимание! Проверки проходят по номерам паспорта от 000000 - 000006, другие номера выкинут рандомно один из 3-х отчетов с ошибкой поставщика.
        return MvdResponse.createMvdResponse(seria, nomer);
    }

    public Response getDriverIdCheck(String type, String serianomer, String date, String token) {
        validator.validToken(token);
        validator.validType(type, "driver");
        validator.validDate(date);
        validator.validParam(serianomer);

        // Внимание! Имитация ответа примерно в каждом 4 запросе, если достигнуто максимальное количество попыток отправить запрос в ФНС.
        int randomInt = (int) (Math.random() * 4);
        if (randomInt == 2) {
            return new TimeMaxConErrorResponse();
        }
        if (serianomer.equals("1234567890") && date.equals("07.11.2014")) {
            return GibddPositiveResponse.createGibddResponse();
        }
        return new GibddNegativeResponse();
    }

    public Response getTerExtrCheck(String type, String search, String token) {
        validator.validToken(token);
        validator.validType(type, "terextr");
        validator.validParam(search);

        // Внимание! Имитация ответа примерно в каждом 4 запросе, если достигнуто максимальное количество попыток отправить запрос в ФНС.
        int randomInt = (int) (Math.random() * 4);
        if (randomInt == 2) {
            return new TimeMaxConErrorResponse();
        }

        if ("АБАКАРОВ ШАМИЛЬ БАГОМЕДОВИЧ*".contains(search.toUpperCase())) {
            return RosFinMonPositiveResponse.createPositiveResponse();
        }
        return new RosFinMonNegativeResponse();
    }
}
