package com.expense_api.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal amount;
    private String category;
    private Date date;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
