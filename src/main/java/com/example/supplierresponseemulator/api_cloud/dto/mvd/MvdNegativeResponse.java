package com.example.supplierresponseemulator.api_cloud.dto.mvd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MvdNegativeResponse extends MvdResponse {
    private int status;
    private int error;
    private String message;


    // Внимание! Проверки проходят по номерам паспорта от 000000 - 000006, другие номера выкинут рандомно один из 3-х отчетов с ошибкой поставщика.
//    public static MvdResponse createNegativeResponse() {
//        Random random = new Random();
//        int problemID = random.nextInt(4);
//        return switch (problemID) {
//            case (0) -> new MvdNegativeResponse(404, 9000, "Ресурс источник недоступен");
//            case (1) -> new MvdNegativeResponse(404, 1, "Нет ответа от ресурса источника");
//            case (2) ->
//                    new MvdNegativeResponse(404, 1, "Незадокументированный ответ источника. Возможно не верно были переданы seria или nomer");
//            default -> new MvdNegativeResponse(404, 1, "Незадокументированный ответ источника. Возможно не верно были переданы seria или nomer");; // заглушка
//        };
//    }
}
