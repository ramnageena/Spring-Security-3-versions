package com.expense_api.controller;


import com.expense_api.payload.ExpenseRequest;
import com.expense_api.payload.ExpenseResponse;
import com.expense_api.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/saveExpense")
    public ResponseEntity<ExpenseResponse> saveExpense(@Valid @RequestBody ExpenseRequest expenseRequest){
        ExpenseResponse expenseResponse = expenseService.saveExpense(expenseRequest);
        return  new ResponseEntity<>(expenseResponse, HttpStatus.CREATED);
    }
    @GetMapping("/getAllExpense")
    public ResponseEntity<List<ExpenseResponse>> getAllExpense(){
        List<ExpenseResponse> expenseResponse = expenseService.getAllExpense();
        return  new ResponseEntity<>(expenseResponse, HttpStatus.FOUND);
    }
    @GetMapping("/getExpense/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long expenseId){
        ExpenseResponse expenseResponse = expenseService.getExpenseById(expenseId);
        return  new ResponseEntity<>(expenseResponse, HttpStatus.FOUND);
    }
    @PutMapping("/updateExpense/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense( @PathVariable Long expenseId,@Valid @RequestBody ExpenseRequest expenseRequest){
        ExpenseResponse expenseResponse = expenseService.updateExpense(expenseId,expenseRequest);
        return  new ResponseEntity<>(expenseResponse, HttpStatus.OK);
    }
    @DeleteMapping("/deleteExpense/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
        expenseService.deleteExpense(expenseId);
        return  new ResponseEntity<>("Expense Deleted Successfully!!!", HttpStatus.OK);
    }

   /* @GetMapping("/getAllExpense/category")
    public ResponseEntity<List<ExpenseResponse>> readByCategory(@RequestParam String category){
        List<ExpenseResponse> expenseResponse = expenseService.readByCategory(category);
        return  new ResponseEntity<>(expenseResponse, HttpStatus.FOUND);
    }

    @GetMapping("/getAllExpense/name")
    public ResponseEntity<List<ExpenseResponse>> readByName(@RequestParam String name){
        List<ExpenseResponse> expenseResponse = expenseService.readByName(name);
        return  new ResponseEntity<>(expenseResponse, HttpStatus.FOUND);
    }

    @GetMapping("/getAllExpense/date")
    public ResponseEntity<List<ExpenseResponse>> readByDate(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate){

        List<ExpenseResponse> expenseResponse = expenseService.readByDate(startDate,endDate);
        return  new ResponseEntity<>(expenseResponse, HttpStatus.FOUND);
    }*/

}
