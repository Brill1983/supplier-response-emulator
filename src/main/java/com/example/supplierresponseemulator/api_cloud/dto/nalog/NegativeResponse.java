package com.example.supplierresponseemulator.api_cloud.dto.nalog;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NegativeResponse extends Response {
    private final int status = 200;
    private final boolean found = false;
    private final String message = "Информация об ИНН не найдена";
    private Inquiry inquiry = new Inquiry(0.8F, 10462.11, 1, 1);

}
