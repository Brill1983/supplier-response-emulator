package com.example.supplierresponseemulator.api_cloud.dto.mvd;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import com.example.supplierresponseemulator.api_cloud.dto.TimeMaxConErrorResponse;

import java.util.Random;

public class MvdResponse extends Response {

    public static Response createMvdResponse(String seria, String nomer) {
        String message;
        if (!seria.equals("0000")) {
            return MvdNegativeResponse.createNegativeResponse();
        }
        switch (nomer) {
            case ("000000") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000000 получен ответ о том, что данный паспорт «Cреди недействительных не значится».";
                return new MvdPositiveResponse(200, 0, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            case ("000001") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000001 получен ответ о том, что данный паспорт «Не действителен Не действителен (ИЗЬЯТ, УНИЧТОЖЕН)».";
                return new MvdPositiveResponse(200, 1, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            case ("000002") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000002 получен ответ о том, что данный паспорт «Не действителен (ТЕХНИЧЕСКИЙ БРАК)».";
                return new MvdPositiveResponse(200, 2, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            case ("000003") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000003 получен ответ о том, что данный паспорт «Не действителен (ЗАМЕНЕН НА НОВЫЙ)».";
                return new MvdPositiveResponse(200, 3, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            case ("000004") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000004 получен ответ о том, что данный паспорт «Не действителен (УТРАТА ПАСПОРТА)».";
                return new MvdPositiveResponse(200, 4, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            case ("000005") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000005 получен ответ о том, что данный паспорт «Не действителен (В СВЯЗИ СО СМЕРТЬЮ ВЛАДЕЛЬЦА)».";
                return new MvdPositiveResponse(200, 5, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            case ("000006") -> {
                message = "По Вашему запросу о действительности паспорта РФ 0000 № 000006 получен ответ о том, что данный паспорт «Не действителен (ЧИСЛИТСЯ В РОЗЫСКЕ)».";
                return new MvdPositiveResponse(200, 6, message, new Inquiry(0.8F, 134.50, 1, 1));
            }
            default -> {
                return MvdNegativeResponse.createNegativeResponse();
            }
        }
    }

    // Внимание! Проверки проходят по номерам паспорта от 000000 - 000006, другие номера выкинут рандомно один из 4-х отчетов с ошибкой поставщика.
    public static Response createNegativeResponse() {
        Random random = new Random();
        int problemID = random.nextInt(4);
        return switch (problemID) {
            case (0) -> new MvdNegativeResponse(404, 9000, "Ресурс источник недоступен");
            case (1) -> new MvdNegativeResponse(404, 1, "Нет ответа от ресурса источника");
            case (2) ->
                    new MvdNegativeResponse(404, 1, "Незадокументированный ответ источника. Возможно не верно были переданы seria или nomer");
            default -> new TimeMaxConErrorResponse();
        };
    }
}
