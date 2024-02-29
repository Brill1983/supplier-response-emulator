package com.example.supplierresponseemulator.api_cloud.utils;

import com.example.supplierresponseemulator.api_cloud.exceptions.SupplierException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.example.supplierresponseemulator.api_cloud.utils.Constants.API_CLOUD_USER_TOKEN;
import static com.example.supplierresponseemulator.api_cloud.utils.Constants.DATE_FORMAT;

public class Validator {

    public boolean validFsspReqParams(String type, String lastname, String firstname, String birthdate, String region,
                                      String token, int searchAll, int onlyActual) {
        validToken(token);
        validType(type, "physical");
        validLastnameFirstname(lastname, firstname);
        validDate(birthdate);

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

    public boolean validInnReqParams(String type, String firstname, String lastname, String birthdate,
                                     String serianomer, String token) {
        validToken(token);
        validType(type, "inn");
        validLastnameFirstname(lastname, firstname);
        validDate(birthdate);

        if (lastname.isBlank() || firstname.isBlank()) {
            return false;
        }
        return true;
    }

    public void validToken(String token) {
        if (token == null) {
            throw new SupplierException("502", "MISSING_REQUIRED_TOKEN_PARAMETER");
        }
        if (token.length() != 32) {
            throw new SupplierException("499", "WRONG_TOKEN_KEY");
        }
        if (!token.equals(API_CLOUD_USER_TOKEN)) {
            throw new SupplierException("503", "TOKEN_NOT_REGISTERED_IN_THE_SYSTEM");
        }
    }

    public void validLastnameFirstname(String firstname, String lastname) {
        if (lastname == null || firstname == null) {
            throw new SupplierException("766", "MISSING_MANDATORY_PARAMETER"); // Могут быть сообщения с указанием какой параметр отсутствует
        }
    }

    public void validType(String type, String sample) {
        if (type == null || !type.equals(sample)) {
            throw new SupplierException("500", "MISSING_REQUIRED_TYPE_PARAMETER");
        }
    }

    public void validDate(String birthdate) {
        if (birthdate == null) {
            throw new SupplierException("766", "MISSING_MANDATORY_PARAMETER"); // Могут быть сообщения с указанием какой параметр отсутствует
        }
        try {
            LocalDate.parse(birthdate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new SupplierException("015", "DATE_ERROR");
        }
    }
}
