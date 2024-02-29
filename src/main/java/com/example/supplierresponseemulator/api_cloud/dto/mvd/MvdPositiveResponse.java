package com.example.supplierresponseemulator.api_cloud.dto.mvd;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MvdPositiveResponse extends MvdResponse {
    private int status;
    private int rezultat;
    private String info;
    private Inquiry inquiry;
}
