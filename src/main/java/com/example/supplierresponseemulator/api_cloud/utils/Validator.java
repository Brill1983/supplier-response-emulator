package com.example.supplierresponseemulator.api_cloud.utils;

import com.example.supplierresponseemulator.api_cloud.dto.Response;
import com.example.supplierresponseemulator.api_cloud.dto.SupplierException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.example.supplierresponseemulator.api_cloud.utils.Constants.API_CLOUD_USER_TOKEN;
import static com.example.supplierresponseemulator.api_cloud.utils.Constants.DATE_FORMAT;

public class Validator {

    public boolean validFsspReqParams(String lastname, String firstname, String region, int searchAll, int onlyActual) {

        if (lastname.isBlank() || firstname.isBlank()) {
            return false;
        }
        // отдельно, потому что тут можно усложнять проверку
        if (!region.equals("-1")) {
            return false;
        }
        if (searchAll != 0 && searchAll != 1) {
            return false;
        }
        return onlyActual == 0 || searchAll == 1;
    }

    public Response validToken(String token) {
        if (token == null) {
            return new SupplierException("502", "MISSING_REQUIRED_TOKEN_PARAMETER");
        }
        if (token.length() != 32) {
            return new SupplierException("499", "WRONG_TOKEN_KEY");
        }
        if (!token.equals(API_CLOUD_USER_TOKEN)) {
            return new SupplierException("503", "TOKEN_NOT_REGISTERED_IN_THE_SYSTEM");
        }
        return null;
    }

    public Response validType(String type, String sample) {
        if (type == null || !type.equals(sample)) {
            return new SupplierException("500", "MISSING_REQUIRED_TYPE_PARAMETER");
        }
        return null;
    }

    public Response validParam(String param) {
        if (param == null) {
            return new SupplierException("766", "MISSING_MANDATORY_PARAMETER");
        }
        return null;
    }

    public Response validDate(String date) {
        if (date == null) {
            return new SupplierException("766", "MISSING_MANDATORY_PARAMETER"); // Могут быть сообщения с указанием какой параметр отсутствует
        }
        try {
            LocalDate.parse(date, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return new SupplierException("015", "DATE_ERROR");
        }
        return null;
    }
}
