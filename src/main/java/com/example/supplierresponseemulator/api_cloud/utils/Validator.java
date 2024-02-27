package com.example.supplierresponseemulator.api_cloud.utils;

import com.example.supplierresponseemulator.api_cloud.exceptions.BadParameterException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.example.supplierresponseemulator.api_cloud.utils.Constants.API_CLOUD_USER_TOKEN;
import static com.example.supplierresponseemulator.api_cloud.utils.Constants.DATE_FORMAT;

public class Validator {

    public boolean validFsspReqParams(String type, String lastname, String firstname, String birthdate, String region,
                                      String token, int searchAll, int onlyActual) {
        validToken(token);
        if (!type.equals("physical") || StringUtils.isBlank(lastname) || StringUtils.isBlank(firstname)) {
            return false;
        }
        try {
            LocalDate birthday = LocalDate.parse(birthdate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
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

    public void validToken(String token) {
        if (!token.equals(API_CLOUD_USER_TOKEN)) {
            throw new BadParameterException("Передан пустой или не существующий токен API");
        }
    }
}
