package com.expense_api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse {
    private Integer statusCode;
    private String message;
    private Date timestamp;

    public APIResponse(String message, Integer statusCode, Date timestamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }
}
