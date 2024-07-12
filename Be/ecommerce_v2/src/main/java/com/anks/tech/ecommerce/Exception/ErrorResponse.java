package com.anks.tech.ecommerce.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String detailMessage;
    private Object error;
    private int code;
    private String moreInformation;
}
