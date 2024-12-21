package com.expense_api.service;

import com.expense_api.entity.Expense;
import com.expense_api.payload.ExpenseRequest;
import com.expense_api.payload.ExpenseResponse;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {

    ExpenseResponse saveExpense(ExpenseRequest expenseRequest);
    List<ExpenseResponse> getAllExpense();
    ExpenseResponse getExpenseById(Long expenseId);
    ExpenseResponse updateExpense(Long expenseId,ExpenseRequest expenseRequest);
    String deleteExpense(Long expenseId);
//    List<ExpenseResponse>readByCategory(String category);
//    List<ExpenseResponse>readByName(String name);
//    List<ExpenseResponse>readByDate(Date startDate,Date endDate);
}
