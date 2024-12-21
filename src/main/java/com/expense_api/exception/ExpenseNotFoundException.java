package com.expense_api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseNotFoundException extends  RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ExpenseNotFoundException(String message) {
        super(message);
    }
    public ExpenseNotFoundException(String fieldName, long fieldValue, String resourceName) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }



    public ExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
