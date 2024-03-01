package com.example.supplierresponseemulator.api_cloud.dto.fedres_banckrupt;

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
public class BankruptNegativeResponse extends Response {
    private int status;
    private int num; // Количество найденных записей
    private String message;
    private Inquiry inquiry;
}
