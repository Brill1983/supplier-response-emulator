package com.example.supplierresponseemulator.api_cloud.dto.fedres_banckrupt;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankruptPositiveResponse extends Response {
    private int status;
    private int totalCount;
    private List<Rez> rez;
    private Inquiry inquiry;

}
