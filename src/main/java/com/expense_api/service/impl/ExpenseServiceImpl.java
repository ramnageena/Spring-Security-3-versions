package com.expense_api.service.impl;


import com.expense_api.entity.Expense;
import com.expense_api.exception.ExpenseNotFoundException;
import com.expense_api.payload.ExpenseRequest;
import com.expense_api.payload.ExpenseResponse;
import com.expense_api.repository.ExpenseRepository;
import com.expense_api.service.ExpenseService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExpenseServiceImpl implements ExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);


    private final ExpenseRepository expenseRepository;

    private final ModelMapper modelMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ExpenseResponse saveExpense(ExpenseRequest expenseRequest) {
        logger.info("Starting to save expense data into the database.");
        Expense expense = modelMapper.map(expenseRequest, Expense.class);

        try {
            /* here we are saving user id before expense saving to db */

            /* end*/

            Expense savedExpense = expenseRepository.save(expense);
            ExpenseResponse expenseResponse = modelMapper.map(savedExpense, ExpenseResponse.class);
            logger.info("Expense data saved successfully with ID: {}", expenseResponse.getId());
            return expenseResponse;

        } catch (Exception e) {
            logger.error("Error occurred while saving the expense data: {}", e.getMessage());
            throw new ExpenseNotFoundException("Failed to save expense.", e);
        }
    }

    @Override
    public List<ExpenseResponse> getAllExpense() {
        logger.info("::Starting to get all expense data into the database::");


        try {
            List<Expense> allExpenses = expenseRepository.findAll();

            if (allExpenses.isEmpty()) {
                logger.warn("No expenses found.");
                throw new ExpenseNotFoundException("No expenses found.");
            }
            List<ExpenseResponse> expenseResponseList = allExpenses.stream().map(expense -> modelMapper.map(expense, ExpenseResponse.class)).collect(Collectors.toList());
            logger.info("All Expense data got successfully..... size ::{} ", expenseResponseList.size());
            return expenseResponseList;

        } catch (Exception e) {
            logger.error("Error occurred while getting all the expense data: {}", e.getMessage());
            throw new ExpenseNotFoundException("Failed to fetch expenses.", e);
        }
    }

    @Override
    @Transactional
    public ExpenseResponse getExpenseById(Long expenseId) {
        logger.info("::Starting to get  expense data into the database by particular Id::");
        try {
            if (expenseId == null) {
                logger.error("Expense ID is null.");
                throw new IllegalArgumentException("Expense ID cannot be null.");
            }
            Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException("Expense", "ID", expenseId));
            ExpenseResponse expenseResponse = modelMapper.map(expense, ExpenseResponse.class);

            logger.info(" Expense data got successfully with id :: {} ", expenseResponse.getId());
            return expenseResponse;

        } catch (Exception e) {
            logger.error("Error occurred while getting  the expense data : {}", e.getMessage());
            throw new ExpenseNotFoundException("Expense with ID " + expenseId + " not found.");
        }
    }

    @Transactional
    @Override
    public ExpenseResponse updateExpense(Long expenseId, ExpenseRequest expenseRequest) {
        logger.info("Updating expense with ID: {}", expenseId);
        ExpenseResponse existingExpense = getExpenseById(expenseId);
        existingExpense.setName(expenseRequest.getName() != null ? expenseRequest.getName() : existingExpense.getName());
        existingExpense.setAmount(expenseRequest.getAmount() != null ? expenseRequest.getAmount() : existingExpense.getAmount());
        existingExpense.setDescription(expenseRequest.getDescription() != null ? expenseRequest.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expenseRequest.getCategory() != null ? expenseRequest.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expenseRequest.getDate() != null ? expenseRequest.getDate() : existingExpense.getDate());

        try {
            Expense expense = modelMapper.map(existingExpense, Expense.class);
            Expense updatedExpense = expenseRepository.save(expense);
            ExpenseResponse expenseResponse = modelMapper.map(updatedExpense, ExpenseResponse.class);
            logger.info(" Update Expense data got successfully with id :: {} ", expenseResponse.getId());
            return expenseResponse;

        } catch (Exception e) {
            logger.error("Error occurred while updating  the expense data : {}", e.getMessage());
            throw new ExpenseNotFoundException("Failed to update expense.", e);
        }


    }

    @Override
    @Transactional
    public String deleteExpense(Long expenseId) {
        logger.info("Deleting expense with ID: {}", expenseId);
        ExpenseResponse expenseResponse = getExpenseById(expenseId);

        try {
            Expense expense = modelMapper.map(expenseResponse, Expense.class);
            expenseRepository.delete(expense);

            logger.info(" Expense Deleted successfully with id :: {} ", expense.getId());
            return "Expense Deleted Successfully ";

        } catch (Exception e) {
            logger.error("Error occurred while deleting  the expense data : {}", e.getMessage());
            throw new ExpenseNotFoundException("Expense with ID " + expenseId + " not found.");
        }
    }

  /*  @Override
    public List<ExpenseResponse> readByCategory(String category) {
        logger.info("Getting  all expense with category: {}", category);
        try {
            List<Expense> expenseList = expenseRepository.findByCategory(category);
            List<ExpenseResponse> expenseResponseList = expenseList.stream().map(x -> modelMapper.map(x, ExpenseResponse.class)).toList();
            logger.info("Got all expenses base on category successfully ...size ::{}", expenseList.size());
            return expenseResponseList;
        } catch (Exception e) {
            logger.error("Error occurred while getting all the expense data with category: {}", e.getMessage());
            throw new ExpenseNotFoundException("Failed to fetch expenses.", e);
        }

    }

    @Override
    public List<ExpenseResponse> readByName(String name) {
        logger.info("Getting  all expense with name : {}", name);
        try {
            List<Expense> expenseList = expenseRepository.findByNameContaining(name);
            List<ExpenseResponse> expenseResponseList = expenseList.stream().map(x -> modelMapper.map(x, ExpenseResponse.class)).toList();
            logger.info("Got all expenses base on name successfully ...size ::{}", expenseList.size());
            return expenseResponseList;
        } catch (Exception e) {
            logger.error("Error occurred while getting all the expense data with name: {}", e.getMessage());
            throw new ExpenseNotFoundException("Failed to fetch expenses.", e);
        }
    }

    @Override
    public List<ExpenseResponse> readByDate(Date startDate, Date endDate) {
        logger.info("Getting  all expense with Date with startDate : {} and endDate : {} ", startDate, endDate);
        if (startDate == null) {
            startDate = new Date(0);
        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }
        try {
            List<Expense> expenseList = expenseRepository.findByDateBetween(startDate, endDate);
            List<ExpenseResponse> expenseResponseList = expenseList.stream().map(x -> modelMapper.map(x, ExpenseResponse.class)).toList();
            logger.info("Got all expenses base on date successfully ...size ::{}", expenseList.size());
            return expenseResponseList;
        } catch (Exception e) {
            logger.error("Error occurred while getting all the expense data with date : {}", e.getMessage());
            throw new ExpenseNotFoundException("Failed to fetch expenses.", e);
        }
    }*/
}
