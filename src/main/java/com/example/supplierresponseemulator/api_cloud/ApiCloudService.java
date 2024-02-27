package com.example.supplierresponseemulator.api_cloud;

import com.example.supplierresponseemulator.api_cloud.dto.fssp.FsspEnfProceedings;
import com.example.supplierresponseemulator.api_cloud.dto.fssp.FsspResponse;
import com.example.supplierresponseemulator.api_cloud.dto.fssp.FsspWrongOrEmpyResponse;
import com.example.supplierresponseemulator.api_cloud.dto.fssp.Record;
import com.example.supplierresponseemulator.api_cloud.utils.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiCloudService {

    private final Validator validator = new Validator();

    public FsspResponse fsspEnfPrecessing(String type, String lastname, String firstname, String secondname,
                                          String birthdate, String region, String token, int searchAll, int onlyActual) {
        if (!validator.validFsspReqParams(type, lastname, firstname, birthdate, region, token, searchAll, onlyActual)) {
            return FsspWrongOrEmpyResponse.createWrongOrEmptyResponse();
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
}
