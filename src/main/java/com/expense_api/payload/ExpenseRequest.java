package com.expense_api.payload;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    @NotBlank(message = "Expense name is required.")
    @Size(min = 1, max = 100, message = "Expense name must be between 1 and 100 characters.")
    private String name;

    @Size(max = 255, message = "Expense description cannot exceed 255 characters.")
    private String description;

    @NotNull(message = "Expense amount is required.")
    @DecimalMin(value = "0.01", inclusive = true, message = "Expense amount must be greater than or equal to 0.01.")
    private BigDecimal amount;

    @NotBlank(message = "Category is required.")
    @Size(min = 1, max = 50, message = "Category must be between 1 and 50 characters.")
    private String category;

    @NotNull(message = "Date is required.")
    private Date date;


}
