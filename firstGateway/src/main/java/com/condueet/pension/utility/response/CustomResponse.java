package com.condueet.pension.utility.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

//    private Boolean status;
//    private AccessData data;
//    private String message;
//    private Boolean error;

    private String message;

    private Object data;

    private int code;

    private boolean status;

    public CustomResponse() {
        this.message = message;
        this.data = data;
        this.code = HttpStatus.OK.value();
        this.status = status;
    }

    public CustomResponse(Object data) {
        this.data = data;
    }
}
