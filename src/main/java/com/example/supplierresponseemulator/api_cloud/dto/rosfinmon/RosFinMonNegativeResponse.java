package com.example.supplierresponseemulator.api_cloud.dto.rosfinmon;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RosFinMonNegativeResponse extends Response {
    private int status = 200;
    private boolean found = false;
    private int count = 0;
    private Inquiry inquiry = new Inquiry(0.3F, 2768.03, 1, 1);
}
