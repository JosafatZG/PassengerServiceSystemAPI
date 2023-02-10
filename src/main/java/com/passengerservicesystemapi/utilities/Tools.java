package com.passengerservicesystemapi.utilities;

import java.io.Serial;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Tools extends LogTrace {

    @Serial
    private static final long serialVersionUID = 1351682443272809940L;
    private String valuesFromValidateUserResponse;

    public Map<String, Object> getResponse(Map<String, Object> response, Object[] at) {
        if(at[0] != null) {response.put(AppMicroPSSConstants.ATTRIBUTE_GENERAL, at[0]);}
        if(at[1] != null) {response.put(AppMicroPSSConstants.ATTRIBUTE_GENERAL, at[1]);}
        if(at[2] != null) {response.put(AppMicroPSSConstants.ATTRIBUTE_GENERAL, at[2]);}
        if(at[3] != null) {response.put(AppMicroPSSConstants.ATTRIBUTE_GENERAL, at[3]);}

        return response;
    }

    public String getValuesFromResponse(Map<String, Object> validateResponse) {
        valuesFromValidateUserResponse = "{ ";

        validateResponse.forEach((key, value) -> {
            if (AppMicroPSSConstants.ATTRIBUTE_GENERAL.equalsIgnoreCase(key)) {
                valuesFromValidateUserResponse += key + " = " + value + ", ";
            }
        });

        valuesFromValidateUserResponse = valuesFromValidateUserResponse.substring(0, valuesFromValidateUserResponse.length() - 2) + " }";

        return valuesFromValidateUserResponse;
    }

    public ResponseEntity<Map<String, Object>> getMapResponseEntity(Map<String, Object> response, HttpStatus httpStatus, String id, String errorMessage) {
        Map<String, Object> valueOfResponse = this.getResponse(
                response,
                new Object[] {
                        AppMicroPSSConstants.CODE_ERROR_SVR,
                        AppMicroPSSConstants.CODE_ERROR_SVR,
                        AppMicroPSSConstants.MSG_ERROR_SERV_PROV,
                        errorMessage
                }
        );
        this.instantLogId(this.getValuesFromResponse(valueOfResponse), id);

        return new ResponseEntity<>(valueOfResponse, httpStatus);
    }
}
